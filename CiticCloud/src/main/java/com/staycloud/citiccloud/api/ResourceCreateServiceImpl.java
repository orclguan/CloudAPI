package com.staycloud.citiccloud.api.service.impl;

import com.google.common.collect.Maps;
import com.staycloud.citiccloud.api.domain.ExistedResourceResponse;
import com.staycloud.citiccloud.api.domain.ResourceCreateRequest;
import com.staycloud.citiccloud.api.domain.ResourceCreateResponse;
import com.staycloud.citiccloud.api.service.ResourceCreateService;
import com.staycloud.citiccloud.api.service.internal.AbstractResourceService;
import com.staycloud.citiccloud.api.support.SimpleActionResponse;
import com.staycloud.citiccloud.core.*;
import com.staycloud.citiccloud.core.util.ExceptionHelper;
import com.staycloud.citiccloud.data.dict.ResourceActionParamType;
import com.staycloud.citiccloud.data.dict.ResourceActionType;
import com.staycloud.citiccloud.data.model.*;
import com.staycloud.citiccloud.data.repository.*;
import com.staycloud.citiccloud.provider.aliyun.AliyunClientException;
import com.staycloud.common.lang.BizServiceException;
import com.staycloud.common.lang.ErrorMeta;
import com.staycloud.common.lang.serialize.JSON;
import com.staycloud.common.lang.spi.ExtensionLoader;
import com.staycloud.data.api.Filter;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by ccl on 16/6/16.
 */
@Service("resourceCreateService")
public class ResourceCreateServiceImpl extends AbstractResourceService implements ResourceCreateService {

    @Autowired
    ResourceProviderRepository resourceProviderRepository;
    @Autowired
    ResourceRepository resourceRepository;
    @Autowired
    ResourceActionRepository resourceActionRepository;
    @Autowired
    ResourceActionParamRepository resourceActionParamRepository;
    @Autowired
    DictGroupRepository dictGroupRepository;
    @Autowired
    DictRepository dictRepository;
    @Autowired
    ApiVersionRepository apiVersionRepository;
    @Autowired
    MetadataRepository metadataRepository;
    @Autowired
    ResourceInstanceRepository resourceInstanceRepository;
    @Autowired
    ResourceActionInstanceRepository resourceActionInstanceRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    ResourceInstanceService resourceInstanceService;

    /**
     * 服务实例交付
     *
     * @return
     */
    @Override
    public SimpleActionResponse createInstance(ResourceCreateRequest request) {
        //200 OK
        //给出ID的服务实例已经存在并且所有配置与给出的配置一致，预期的返回内容为 {}。
        Filter filterInstance = new Filter().equal("citicInstanceId", request.getInstanceId());
        ResourceInstance resourceInstance = resourceInstanceRepository.findOneByModel(filterInstance);
        ResourceActionInstance resourceActionInstance = null;
//        if (resourceInstance != null && StringUtils.isNotBlank(resourceInstance.getUid())) {
//            Filter filterActionInstance = new Filter().equal("resourceInstance.id", resourceInstance.getId()).and(new Filter().equal("resourceAction.type", ResourceActionType.create));
//            resourceActionInstance = resourceActionInstanceRepository.findOneByModel(filterActionInstance);
//            if (resourceActionInstance != null) {
//                if (JSON.toString(request.getParameters()).equals(resourceActionInstance.getParams())) {
//                    ExistedResourceResponse response = new ExistedResourceResponse(request.getRequestId());
//                    response.setSupplierInstanceId(resourceInstance.getUid());
//                    return response;
//                }
//            }
//            //409 Conflict
//            //给出ID的服务实例已经存在，但是配置与给出的配置不一致。预期的返回内容为 {}。
//            ConflictResourceResponse response = new ConflictResourceResponse(request.getRequestId());
//            response.setSupplierInstanceId(resourceInstance.getUid());
//            return response;
//        }
        if (resourceInstance != null) {
            throw new BizServiceException("该实例ID已经被使用，请重新更换一个!", "citicInstanceId is used");
        }

        //1.根据service_id查找resource表获得供应商id
        Resource resource = resourceRepository.findModelById(request.getServiceId());
        String provider = resourceProviderRepository.findById(resource.getResourceProvider().getId()).getName();
        //获得用户
        Filter filter2 = new Filter().equal("orgId", request.getOrgid()).and(new Filter().equal("resourceProvider.id", resource.getResourceProvider().getId()));
        Account account = accountRepository.findOneByModel(filter2);
        if (account == null) {
            //创建用户账户
            AccountService accountService = ExtensionLoader.getExtension(AccountService.class, provider);
            String ownerID = accountService.createAccount(request.getOrgid());
            AccessKey accessKey = accountService.createAccessKey(ownerID);
            account = new Account();
            account.setOrgId(request.getOrgid());
            account.setUid(ownerID);
            account.setAccesskeyId(accessKey.getAccessKeyId());
            account.setAccesskeySecret(accessKey.getAccessKeySecret());
            account.setResourceProvider(resource.getResourceProvider());
            account = accountRepository.createModel(account);
        }
        //處理開通型服務，服務只需開通一次
        if ("Oss".equals(resource.getName()) || "Ons".equals(resource.getName()) || "CS".equals(resource.getName())) {
            Filter filter = new Filter().equal("uid", account.getUid());
            filter.and(new Filter().equal("resourceName", resource.getName()));
            ResourceInstance instance1 = resourceInstanceRepository.findOneByModel(filter);
            if (null != instance1 && account.getUid().equals(instance1.getUid())) {
                ExistedResourceResponse response = new ExistedResourceResponse(request.getRequestId());
                response.setSupplierInstanceId(instance1.getUid());
                return response;
            }
        }
        //3.根据resourceId和type获得action名称
        Filter filter1 = new Filter().equal("resource.id", request.getServiceId()).and(new Filter().equal("type", ResourceActionType.create));
        ResourceAction resourceAction = resourceActionRepository.findOneByModel(filter1);
        Boolean flage = false;
        String message = null;
        String errorCode = null;
        String requestId = null;
        ResourceProcessResult processResult = null;
        try {
            String regionId = request.getParameters().get("RegionId");

            if (null == resourceInstance) {
                //5.向resource_instance存数据
                resourceInstance = new ResourceInstance();
                //没有用户去阿里创建一个用户,再插入数据库
                resourceInstance.setAccount(account);
                resourceInstance.setResource(resource);
                //開通型服務不需要區分地域
                if (!Constants.OPEN_SERVICE_ACTION.equals(resourceAction.getName())) {
                    resourceInstance.setRegionId(null == regionId ? Constants.DEFAULT_REGION : regionId);
                }
                resourceInstance.setResourceName(resource.getName());
                resourceInstance.setCiticInstanceId(request.getInstanceId());
                resourceInstance.setAvailable(flage);
                resourceInstance = resourceInstanceRepository.createModel(resourceInstance);
            }

            if (null == resourceActionInstance) {
                //6.向resource_action_instance存数据
                resourceActionInstance = new ResourceActionInstance();
                resourceActionInstance.setResource(resource);
                resourceActionInstance.setResourceInstance(resourceInstance);
                resourceActionInstance.setResourceAction(resourceAction);
                resourceActionInstance.setParams(JSON.toString(request));
                resourceActionInstance.setIsSuccess(flage);
                resourceActionInstance = resourceActionInstanceRepository.createModel(resourceActionInstance);
            }

            //調用供應商服務
            ResourceTemplate template = new ResourceTemplate(resource.getName(), resource.getProviderApiVersion(), resourceAction.getName());
            template.setOwnerId(account.getUid());
            template.setInstanceId(request.getInstanceId());
            //数据转换
            if (null != regionId) {
                template.setRegionId(regionId);
            }
            //end
            Map<String, String> parameters = request.getParameters();
            if (request.getParameters() == null) {
                parameters = Maps.newHashMap();
            }
            template.setParameters(parameters);

            if (request.getPricingModel() != null) {
                template.setPricingModel(request.getPricingModel());
            }

            //根据供应商 调用对应 服务和action
            ResourceService resourceService = ExtensionLoader.getExtension(ResourceService.class, provider);
            resourceService.setResourceInstanceService(resourceInstanceService);

            //處理開通服務
            processResult = resourceService.process(template);
            requestId = processResult.getRequestId();

            flage = true;
        } catch (Exception e) {
            ErrorMeta errorMeta = ExceptionHelper.toActionError(e);
            message = errorMeta.getMessage().getMessage();
            errorCode = errorMeta.getErrCode();
            if (e instanceof AliyunClientException) {
                requestId = ((AliyunClientException) e).getRequestId();
            }
            logger.debug("。。。。。。errorMessage is : " + message + "。。。。。。" + "errorCode is :" + errorCode + "。。。。。。", e);
            e.printStackTrace();
        }

        //_1获得action对应的参数//_2根据is_instance_id = true 查找对应实例名
        Filter filterp = new Filter().equal("resourceAction.id", resourceAction.getId())
                .and(new Filter().equal("type", ResourceActionParamType.output))
                .and(new Filter().equal("isInstanceId", true));
        ResourceActionParam param = resourceActionParamRepository.findOneByModel(filterp);
        if (param == null) {
            throw new BizServiceException("找不到对应的Action参数!", "No Instance Param");
        }
        System.out.println("processResult: " + processResult);
        String instanceId = param.getName();
        if (null != processResult) {
            resourceInstance.setUid(processResult.getResult().get(instanceId));
        }
        if (resourceInstance.getUid() != null) {
            resourceInstance.setAvailable(flage);
        }
        resourceInstance = resourceInstanceRepository.updateModel(resourceInstance);

        //更新狀態
        resourceActionInstance.setIsSuccess(flage);
        resourceActionInstance.setErrorMessage(message);
        resourceActionInstance.setErrorCode(errorCode);

        // 记录请求编号request ID
        resourceActionInstance.setRequestId(requestId);
        resourceActionInstanceRepository.updateModel(resourceActionInstance);

        if (null != errorCode) {
            throw new BizServiceException(message, errorCode);
        }

        ///201 Created
        //服务实例已成功创建，预期的返回内容为 {}。
        ResourceCreateResponse createResponse = new ResourceCreateResponse(request.getRequestId());
        createResponse.setSupplierInstanceId(resourceInstance.getUid());
        createResponse.setCreateTime(resourceInstance.getUpdateTime().toString(DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")));
        return createResponse;
    }
}
