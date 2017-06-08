/*package com.staycloud.citiccloud.api.resource;

import com.staycloud.citiccloud.api.ActionResponse;
import com.staycloud.citiccloud.api.action.*;
import com.staycloud.citiccloud.api.domain.*;
import com.staycloud.citiccloud.api.service.ApiService;
import com.staycloud.citiccloud.api.support.SimpleActionRequest;
import com.staycloud.citiccloud.core.Constants;
import com.staycloud.common.lang.Configs;
import com.staycloud.common.lang.utils.ObjectId;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

*//**
 * @author lulin
 * @date 2016/6/12.
 *//*
@Component
@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class ApiResource {

    @Inject
    ApiService apiService;

    @Context
    HttpServletRequest request;

    @Inject
    CatalogAction catalogAction;

    @Inject
    ResourceCreateAction resourceCreateAction;

    @Inject
    CreateWorkordersAction createWorkordersAction;

    @Inject
    CreateWorkordersContentsAction createWorkordersContentsAction;

    @Inject
    GetWorkordersAction getWorkordersAction;

    @Inject
    GetWorkordersContentsAction getWorkordersContentsAction;

    @Inject
    ResourceManageAction resourceManageAction;

    @Inject
    ResourceInstanceOperationAction resourceInstanceOperationAction;

    @Inject
    ResourceDestroyAction resourceDestroyAction;

    @Inject
    AccountCreateAction accountCreateAction;

    @Inject
    SupplierBillDetailAction supplierBillDetailAction;

    @Inject
    SupplierConsumeDetailAction supplierConsumeDetailAction;

    @Inject
    SupplierUsageRecordAction supplierUsageRecordAction;

    @Inject
    SupplierTotalBillAction supplierTotalBillAction;

    @Inject
    ServiceInfoAction serviceInfoAction;


    @GET
    @Path("/catalog")
    public void catalog(@Suspended final AsyncResponse asyncResponse) {
        SimpleActionRequest actionRequest = new SimpleActionRequest();
        actionRequest.setRequestId(ObjectId.get());
        actionRequest.setApiVersion(request.getHeader(Constants.API_VERSION_HEADER));
        actionRequest.setRequestHeaders(getRequestHeaders());
        asyncResponse.resume(apiService.execute(catalogAction, actionRequest));
    }

    @GET
    @Path("/services/{service_id}")
    public void serviceInfo(@Suspended final AsyncResponse asyncResponse, @PathParam("service_id") String serviceId, @QueryParam("org_id") String orgId) {
        ServiceInfoRequest actionRequest = new ServiceInfoRequest();
        actionRequest.setRequestId(ObjectId.get());
        actionRequest.setApiVersion(request.getHeader(Constants.API_VERSION_HEADER));
        actionRequest.setRequestHeaders(getRequestHeaders());
        if (StringUtils.isNotBlank(serviceId)) {
            actionRequest.setServiceId(serviceId);
        }
        if (StringUtils.isNotBlank(orgId)) {
            actionRequest.setOrgId(orgId);
        }
        asyncResponse.resume(apiService.execute(serviceInfoAction, actionRequest));
    }


    @POST
    @Path("/account")
    @Consumes(MediaType.APPLICATION_JSON)
    public void createAccount(@Suspended final AsyncResponse asyncResponse, AccountCreateRequest actionRequest) {
        actionRequest.setRequestId(ObjectId.get());
        actionRequest.setApiVersion(request.getHeader(Constants.API_VERSION_HEADER));
        actionRequest.setRequestHeaders(getRequestHeaders());
        apiService.asyncExecute(accountCreateAction, actionRequest, asyncResponse);
    }

    @PUT
    @Path("/service_instances/{instanceId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void resourceCreate(@Suspended final AsyncResponse asyncResponse, @PathParam("instanceId") String instanceId, ResourceCreateRequest actionRequest) {
        actionRequest.setRequestId(instanceId);
        actionRequest.setInstanceId(instanceId);
        actionRequest.setApiVersion(request.getHeader(Constants.API_VERSION_HEADER));
        actionRequest.setRequestHeaders(getRequestHeaders());
        apiService.asyncExecute(resourceCreateAction, actionRequest, asyncResponse);
    }

    @GET
    @Path("/service_instances")
    public void resourceManage(@Suspended final AsyncResponse asyncResponse, @QueryParam("begin") String begin, @QueryParam("end") String end, @QueryParam("instance_ids") String instances) {
        ResourceManageRequest actionRequest = new ResourceManageRequest();
        actionRequest.setRequestId(ObjectId.get());
        actionRequest.setApiVersion(request.getHeader(Constants.API_VERSION_HEADER));
        actionRequest.setRequestHeaders(getRequestHeaders());
        if (StringUtils.isNotBlank(begin)) {
            actionRequest.setBegin(DateTimeFormat.forPattern("yyyyMMdd").parseDateTime(begin));
        }
        if (StringUtils.isNotBlank(end)) {
            actionRequest.setEnd(DateTimeFormat.forPattern("yyyyMMdd").parseDateTime(end));
        }
        if (StringUtils.isNotBlank(instances)) {
            actionRequest.setInstances(instances.split(","));
        }
        actionRequest.setSupplierId(Configs.getString("api.defaultProvider"));
        asyncResponse.resume(apiService.execute(resourceManageAction, actionRequest));
    }

    @POST
    @Path("/service_instances/{instanceId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void resourceInstanceOperation(@Suspended final AsyncResponse asyncResponse, @PathParam("instanceId") String instanceId, ResourceInstanceOperationRequest actionRequest) {
        actionRequest.setInstanceId(instanceId);
        actionRequest.setRequestId(instanceId);
        actionRequest.setApiVersion(request.getHeader(Constants.API_VERSION_HEADER));
        actionRequest.setRequestHeaders(getRequestHeaders());
        apiService.asyncExecute(resourceInstanceOperationAction, actionRequest, asyncResponse);
    }

    @DELETE
    @Path("/service_instances/{instanceId}")
    public void resourceDestroy(@Suspended final AsyncResponse asyncResponse, @PathParam("instanceId") String instanceId, @QueryParam("accepts_incomplete") Boolean acceptAsync) {
        ResourceDestroyRequest actionRequest = new ResourceDestroyRequest();
        actionRequest.setRequestId(instanceId);
        actionRequest.setInstanceId(instanceId);
        actionRequest.setApiVersion(request.getHeader(Constants.API_VERSION_HEADER));
        actionRequest.setRequestHeaders(getRequestHeaders());
        actionRequest.setAcceptAsync(acceptAsync);
        apiService.asyncExecute(resourceDestroyAction, actionRequest, asyncResponse);
    }

    @GET
    @Path("/service_instances/{instanceId}/last_operation")
    public ActionResponse lastOperation(@PathParam("instanceId") String instanceId) {
        ActionResponse actionResponse = apiService.lastOperation(instanceId);
        return actionResponse;
    }

    @GET
    @Path("/suppliers_service/bill_detail")
    public void billDetail(@Suspended final AsyncResponse asyncResponse, @QueryParam("start_date") String begin, @QueryParam("end_date") String end) {
        SupplierBillDetailRequest actionRequest = new SupplierBillDetailRequest();
        actionRequest.setRequestId(ObjectId.get());
        actionRequest.setApiVersion(request.getHeader(Constants.API_VERSION_HEADER));
        actionRequest.setRequestHeaders(getRequestHeaders());
        if (StringUtils.isNotBlank(begin)) {
            actionRequest.setBegin(DateTimeFormat.forPattern("yyyyMMdd").parseDateTime(begin));
        }
        if (StringUtils.isNotBlank(end)) {
            actionRequest.setEnd(DateTimeFormat.forPattern("yyyyMMdd").parseDateTime(end));
        }
        asyncResponse.resume(apiService.execute(supplierBillDetailAction, actionRequest));
    }

    @GET
    @Path("/suppliers_service/consume_detail")
    public void consumeDetail(@Suspended final AsyncResponse asyncResponse, @QueryParam("start_date") String begin, @QueryParam("end_date") String end) {
        SupplierConsumeDetailRequest actionRequest = new SupplierConsumeDetailRequest();
        actionRequest.setRequestId(ObjectId.get());
        actionRequest.setApiVersion(request.getHeader(Constants.API_VERSION_HEADER));
        actionRequest.setRequestHeaders(getRequestHeaders());
        if (StringUtils.isNotBlank(begin)) {
            actionRequest.setBegin(DateTimeFormat.forPattern("yyyyMMdd").parseDateTime(begin));
        }
        if (StringUtils.isNotBlank(end)) {
            actionRequest.setEnd(DateTimeFormat.forPattern("yyyyMMdd").parseDateTime(end));
        }
        asyncResponse.resume(apiService.execute(supplierConsumeDetailAction, actionRequest));
    }

    @GET
    @Path("/suppliers_service/usage_record/{serviceId}")
    public void usageRecord(@Suspended final AsyncResponse asyncResponse, @PathParam("serviceId") String serviceId, @QueryParam("orgid") String orgid, @QueryParam("start_date") String begin, @QueryParam("end_date") String end, @QueryParam("grain_size") String grainSize) {
        SupplierUsageRecordRequest actionRequest = new SupplierUsageRecordRequest();
        actionRequest.setRequestId(ObjectId.get());
        actionRequest.setApiVersion(request.getHeader(Constants.API_VERSION_HEADER));
        actionRequest.setRequestHeaders(getRequestHeaders());
        if (StringUtils.isNotBlank(begin)) {
            actionRequest.setBegin(DateTimeFormat.forPattern("yyyyMMdd").withZone(DateTimeZone.forID("+0800")).parseDateTime(begin));
        }
        if (StringUtils.isNotBlank(end)) {
            actionRequest.setEnd(DateTimeFormat.forPattern("yyyyMMdd").withZone(DateTimeZone.forID("+0800")).parseDateTime(end));
        }
        actionRequest.setGrainSize(grainSize);
        actionRequest.setServiceId(serviceId);
        actionRequest.setOrgid(orgid);
        asyncResponse.resume(apiService.execute(supplierUsageRecordAction, actionRequest));
    }

    @GET
    @Path("/suppliers_service/total_bill")
    public void totalBill(@Suspended final AsyncResponse asyncResponse, @QueryParam("start_date") String begin, @QueryParam("end_date") String end, @QueryParam("grain_size") String grainSize) {
        SupplierTotalBillRequest actionRequest = new SupplierTotalBillRequest();
        actionRequest.setRequestId(ObjectId.get());
        actionRequest.setApiVersion(request.getHeader(Constants.API_VERSION_HEADER));
        actionRequest.setRequestHeaders(getRequestHeaders());
        if (StringUtils.isNotBlank(begin)) {
            actionRequest.setBegin(DateTimeFormat.forPattern("yyyyMM").parseDateTime(begin));
        }
        if (StringUtils.isNotBlank(end)) {
            actionRequest.setEnd(DateTimeFormat.forPattern("yyyyMM").parseDateTime(end));
        }
        actionRequest.setGrainSize(grainSize);
        actionRequest.setSupplierId(Configs.getString("api.defaultProvider"));
        asyncResponse.resume(apiService.execute(supplierTotalBillAction, actionRequest));
    }

    @POST
    @Path("/workorders")
    @Consumes(MediaType.APPLICATION_JSON)
    public void createWorkorders(@Suspended final AsyncResponse asyncResponse, CreateWorkordersRequest actionRequest) {
        actionRequest.setRequestId(ObjectId.get());
        actionRequest.setApiVersion(request.getHeader(Constants.API_VERSION_HEADER));
        actionRequest.setRequestHeaders(getRequestHeaders());
        asyncResponse.resume(apiService.execute(createWorkordersAction, actionRequest));
    }

    @GET
    @Path("/workorders")
    @Consumes(MediaType.APPLICATION_JSON)
    public void getWorkorders(@Suspended final AsyncResponse asyncResponse, @QueryParam("ids") String ids, @QueryParam("add_start_time") String addStartTime, @QueryParam("add_end_time") String addEndTime, @QueryParam("service_ids") String serviceIds, @QueryParam("page_size") Integer pageSize, @QueryParam("question_status") String questionStatus, @QueryParam("page_start") Integer pageStart) {
        GetWorkordersRequest actionRequest = new GetWorkordersRequest();
        actionRequest.setRequestId(ObjectId.get());
        actionRequest.setApiVersion(request.getHeader(Constants.API_VERSION_HEADER));
        actionRequest.setRequestHeaders(getRequestHeaders());
        if (StringUtils.isNotBlank(ids)) {
            actionRequest.setIds(ids);
        }
        if (StringUtils.isNotBlank(addStartTime)) {
            actionRequest.setAddEndTime(DateTimeFormat.forPattern("yyyyMMddHHmmss").parseDateTime(addStartTime).getMillis());
        }
        if (StringUtils.isNotBlank(addEndTime)) {
            actionRequest.setAddEndTime(DateTimeFormat.forPattern("yyyyMMddHHmmss").parseDateTime(addEndTime).getMillis());
        }
        if (StringUtils.isNotBlank(serviceIds)) {
            actionRequest.setServiceIds(serviceIds);
        }
        if (pageSize != null) {
            actionRequest.setPageSize(pageSize);
        }
        if (StringUtils.isNotBlank(questionStatus)) {
            actionRequest.setQuestionStatus(questionStatus);
        }
        if (pageStart != null) {
            actionRequest.setPageStart(pageStart);
        }
        asyncResponse.resume(apiService.execute(getWorkordersAction, actionRequest));
    }

    @POST
    @Path("/workorders/contents")
    @Consumes(MediaType.APPLICATION_JSON)
    public void createWorkordersContents(@Suspended final AsyncResponse asyncResponse, CreateWorkordersContentsRequest actionRequest) {
        actionRequest.setRequestId(ObjectId.get());
        actionRequest.setApiVersion(request.getHeader(Constants.API_VERSION_HEADER));
        actionRequest.setRequestHeaders(getRequestHeaders());
        asyncResponse.resume(apiService.execute(createWorkordersContentsAction, actionRequest));
    }

    @GET
    @Path("/workorders/contents")
    @Consumes(MediaType.APPLICATION_JSON)
    public void getWorkordersContents(@Suspended final AsyncResponse asyncResponse, @QueryParam("question_id") String questionId) {
        GetWorkordersContentsRequest actionRequest = new GetWorkordersContentsRequest();
        actionRequest.setRequestId(ObjectId.get());
        actionRequest.setApiVersion(request.getHeader(Constants.API_VERSION_HEADER));
        actionRequest.setRequestHeaders(getRequestHeaders());
        if (StringUtils.isNotBlank(questionId)) {
            actionRequest.setQuestionId(questionId);
        }
        asyncResponse.resume(apiService.execute(getWorkordersContentsAction, actionRequest));
    }

    private Map<String, String> getRequestHeaders() {
        Map<String, String> requestHeaders = new HashMap<>();
        if (null != request) {
            // 设置请求头信息
            Enumeration<String> headerNames = request.getHeaderNames();
            while (headerNames.hasMoreElements()) {
                String headerName = headerNames.nextElement();
                requestHeaders.put(headerName, request.getHeader(headerName));
            }

            requestHeaders.put(Constants.REMOTE_HOST, request.getRemoteHost());

        }
        return requestHeaders;
    }
}
*/