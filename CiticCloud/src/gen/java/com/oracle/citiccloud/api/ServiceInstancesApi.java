package com.oracle.citiccloud.api;

import com.oracle.citiccloud.model.*;
import com.oracle.citiccloud.api.ServiceInstancesApiService;
import com.oracle.citiccloud.api.factories.ServiceInstancesApiServiceFactory;

import io.swagger.annotations.ApiParam;
import io.swagger.jaxrs.*;

import com.oracle.citiccloud.model.InstanceCreated;
import com.oracle.citiccloud.model.InstanceUpdated;
import com.oracle.citiccloud.model.Instances;
import com.oracle.citiccloud.model.LastOperationStatus;
import com.oracle.citiccloud.model.ReturnCommon;
import com.oracle.citiccloud.model.ServiceInstance;
import com.oracle.citiccloud.model.ServiceInstanceErrorMsg;
import com.oracle.citiccloud.model.ServiceInstanceModify;

import java.util.List;

import com.oracle.citiccloud.api.NotFoundException;

import java.io.InputStream;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.json.simple.JSONObject;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.*;
import javax.validation.constraints.*;

@Path("/service_instances")

@Produces({ "application/json" })
@io.swagger.annotations.Api(description = "the service_instances API")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-05-17T12:22:27.214+08:00")
public class ServiceInstancesApi  {
   private final ServiceInstancesApiService delegate = ServiceInstancesApiServiceFactory.getServiceInstancesApi();

    @GET
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "服务实例管理.", notes = "此接口点返回供应商系统中某服务的服务实例列表，包括其配置信息和可进行的操作，其中更新操作为特殊处理，在返回更新操作时，配置选项的默认值为此服务实例的当前值。 如果instance_ids为空，则只返回该service_id的20个实例. 示例： 适配器地址/service_instances?service_id=5836a0462fbaaf0aee0db7a2&org_id=abde8d3c-7628-4378-a07f-a7bf6c387531&instance_ids=1caf242f-b3a2-41b5-890b-9fbb82b22585,fbf20ccc-ef9e-4377-bbe1-7c56bd6b996c ", response = Instances.class, responseContainer = "List", tags={ "adapter-management", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "返回供应商系统中某服务的服务实例列表.", response = Instances.class, responseContainer = "List") })
    public Response serviceInstancesGet(@ApiParam(value = "限定查询的serviceid.",required=true) @QueryParam("service_id") String serviceId
,@ApiParam(value = "限定查询的instance id数组，逗号分隔，最多200个.") @QueryParam("instance_ids") List<String> instanceIds
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.serviceInstancesGet(serviceId,instanceIds,securityContext);
    }
    @DELETE
    @Path("/{instance_id}")
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "服务实例释放.", notes = "当适配器收到此接口点时，应释放所指明的服务实例。instance_id是服务实例交付时的唯一标识，通常DELETE操作都没有请求内容。支持异步释放.", response = void.class, tags={ "adapter-management", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "释放成功.", response = void.class),
        
        @io.swagger.annotations.ApiResponse(code = 202, message = "实例释放中.", response = void.class),
        
        @io.swagger.annotations.ApiResponse(code = 422, message = "此服务实例需要进行异步操作。但 accepts_incomplete 不是 true", response = void.class),
        
        @io.swagger.annotations.ApiResponse(code = 200, message = "程序错误", response = void.class) })
    public Response serviceInstancesInstanceIdDelete(@ApiParam(value = "服务实例唯一标识.",required=true) @PathParam("instance_id") String instanceId
,@ApiParam(value = "支持异步释放.",required=true, defaultValue="true") @DefaultValue("true") @QueryParam("accepts_incomplete") Boolean acceptsIncomplete
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.serviceInstancesInstanceIdDelete(instanceId,acceptsIncomplete,securityContext);
    }
    @GET
    @Path("/{instance_id}/last_operation")    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "获取正在进行的上一个操作（仅对异步操作有效）.", notes = "当适配器为服务实例创建、操作（含变更）、释放返回 202 ACCEPTED 时，中信云平台就可以通过 接口获取上一个请求的操作的状态。适配器对这个接口点的响应必须包含 state 域，可选包含 description 域。 state 的有效值包括 in progress，succeeded，和failed。只要返回的是 “state in progress”，中信云平台就可以继续调用last_operation。description域可以给用户提供更多的状态信息，如果需要的话，仅对异步操作有效.", response = LastOperationStatus.class, tags={ "adapter-management", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "返回服务实例交付状态.", response = LastOperationStatus.class) })
    public Response serviceInstancesInstanceIdLastOperationGet(@ApiParam(value = "服务实例ID.",required=true) @PathParam("instance_id") String instanceId
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.serviceInstancesInstanceIdLastOperationGet(instanceId,securityContext);
    }
    @POST
    @Path("/{instance_id}")
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "操作一个服务实例.", notes = "适配器应支持用户对已经存在的服务实例进行变更、开机、关机、充值密码等操作，具体支持操作类型和每种类型需要输入的配置信息由服务实例管理API给出。提示：instance_id是前面交付的服务实例的唯一标识.", response = InstanceUpdated.class, tags={ "adapter-management", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "修改成功.", response = InstanceUpdated.class),
        
        @io.swagger.annotations.ApiResponse(code = 202, message = "实例创建中.", response = InstanceUpdated.class),
        
        @io.swagger.annotations.ApiResponse(code = 422, message = "此服务实例需要进行异步操作。但 accepts_incomplete 不是 true", response = InstanceUpdated.class),
        
        @io.swagger.annotations.ApiResponse(code = 200, message = "程序错误", response = InstanceUpdated.class) })
    public Response serviceInstancesInstanceIdPost(@ApiParam(value = "服务实例唯一标识.",required=true) @PathParam("instance_id") String instanceId
,@ApiParam(value = "服务实例." ,required=true) JSONObject body
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.serviceInstancesInstanceIdPost(instanceId,body,securityContext);
    }
    @PUT
    @Path("/{instance_id}")
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "服务实例交付.", notes = "适配器接收到此接口点调用时，应该按照指定参数创建服务实例。 提示：服务实例的 instance_id 由中信云平台给提供。这个ID会用来对此服务实例进行后继操作，所以适配器必须将此ID与具体的服务实例相关联.", response = InstanceCreated.class, tags={ "adapter-management", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "实例创建成功.", response = InstanceCreated.class),
        
        @io.swagger.annotations.ApiResponse(code = 201, message = "实例创建成功.", response = InstanceCreated.class),
        
        @io.swagger.annotations.ApiResponse(code = 202, message = "实例创建中.", response = InstanceCreated.class),
        
        @io.swagger.annotations.ApiResponse(code = 409, message = "conflict 因资源冲突导致创建失败", response = InstanceCreated.class),
        
        @io.swagger.annotations.ApiResponse(code = 422, message = "此服务实例需要进行异步操作。但 accepts_incomplete 不是 true", response = InstanceCreated.class),
        
        @io.swagger.annotations.ApiResponse(code = 200, message = "程序错误", response = InstanceCreated.class) })
    public Response serviceInstancesInstanceIdPut(@ApiParam(value = "服务实例ID.",required=true) @PathParam("instance_id") String instanceId
,@ApiParam(value = "服务实例." ,required=true) JSONObject body
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.serviceInstancesInstanceIdPut(instanceId,body,securityContext);
    }
    @PUT
    @Path("/verify")
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "服务实例交付前检查", notes = "此接口为可选实现接口，仅当有某个服务 的 “verify_required = ture” 时， 才需要实现此接口。 适配器接收到此接口点调用时，应检查以所提交的参数，是否能生成一个有效的服务实例。 此处所提交的内容与 服务实例交付 PUT /service_instances/{instance_id} 接口所提交的内容一致。", response = ReturnCommon.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "返回服务实例交付检查结果.", response = ReturnCommon.class) })
    public Response serviceInstancesVerifyPut(@ApiParam(value = "实例数据." ,required=true) ServiceInstance instance
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.serviceInstancesVerifyPut(instance,securityContext);
    }
}
