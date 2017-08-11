package com.oracle.citiccloud.api;

import com.oracle.citiccloud.model.*;
import com.oracle.citiccloud.api.WorkordersApiService;
import com.oracle.citiccloud.api.factories.WorkordersApiServiceFactory;

import io.swagger.annotations.ApiParam;
import io.swagger.jaxrs.*;

import com.oracle.citiccloud.model.ContentInsertMsg;
import com.oracle.citiccloud.model.OrdersContentInsert;
import com.oracle.citiccloud.model.PageWorkorder;
import com.oracle.citiccloud.model.Workorder;
import com.oracle.citiccloud.model.WorkorderContent;
import com.oracle.citiccloud.model.WorkorderMsg;

import java.util.List;
import com.oracle.citiccloud.api.NotFoundException;

import java.io.InputStream;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.*;
import javax.validation.constraints.*;

@Path("/workorders")

@Produces({ "application/json" })
@io.swagger.annotations.Api(description = "the workorders API")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-05-17T12:22:27.214+08:00")
public class WorkordersApi  {
   private final WorkordersApiService delegate = WorkordersApiServiceFactory.getWorkordersApi();

    @GET
    @Path("/contents")
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "获取工单内容.", notes = "此接口用于获取工单内容.", response = WorkorderContent.class, tags={ "adapter-management", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "返回工单内容.", response = WorkorderContent.class) })
    public Response workordersContentsGet(@ApiParam(value = "工单ID.",required=true) @QueryParam("question_id") String questionId
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.workordersContentsGet(questionId,securityContext);
    }
    @POST
    @Path("/contents")
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "添加工单内容.", notes = "此接口用于添加工单交流内容.", response = ContentInsertMsg.class, tags={ "adapter-management", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "返回添加结果.", response = ContentInsertMsg.class) })
    public Response workordersContentsPost(@ApiParam(value = "添加工单." ,required=true) OrdersContentInsert body
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.workordersContentsPost(body,securityContext);
    }
    @GET
    
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "获取工单列表.", notes = "此接口用于获取工单列表.", response = PageWorkorder.class, tags={ "adapter-management", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "返回工单列表.", response = PageWorkorder.class) })
    public Response workordersGet(@ApiParam(value = "工单ID列表, 逗号分割， 最多不能超过200个") @QueryParam("ids") String ids
,@ApiParam(value = "开始时间（格式：yyyyMMddHHmmss）") @QueryParam("add_start_time") String addStartTime
,@ApiParam(value = "结束时间（格式：yyyyMMddHHmmss）") @QueryParam("add_end_time") String addEndTime
,@ApiParam(value = "服务ID列表, 逗号分割， 最多不能超过200个") @QueryParam("service_ids") String serviceIds
,@ApiParam(value = "工单状态，默认全部,assigned    已分派工单刚提交; dealing    处理中; wait_feedback    待反馈; feedback    已反馈    用户添加回复时变为此状态; wait_confirm    待确认    处理完工单，置为此状态，待用户确认; confirmed    已确认    用户确认工单已经处理完毕; score    已评价    用户已经评价工单;") @QueryParam("question_status") String questionStatus
,@ApiParam(value = "每页大小,默认20") @QueryParam("page_size") String pageSize
,@ApiParam(value = "开始条数,默认0") @QueryParam("page_start") String pageStart
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.workordersGet(ids,addStartTime,addEndTime,serviceIds,questionStatus,pageSize,pageStart,securityContext);
    }
    @POST
    
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "新建工单.", notes = "此接口用于新建工单.", response = WorkorderMsg.class, tags={ "adapter-management", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "返回创建工单信息.", response = WorkorderMsg.class) })
    public Response workordersPost(@ApiParam(value = "工单内容." ,required=true) Workorder workorder
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.workordersPost(workorder,securityContext);
    }
}
