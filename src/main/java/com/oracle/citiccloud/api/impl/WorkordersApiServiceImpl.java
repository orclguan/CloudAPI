package com.oracle.citiccloud.api.impl;

import com.oracle.citiccloud.api.*;

import com.oracle.citiccloud.model.OrdersContentInsert;
import com.oracle.citiccloud.model.Workorder;

import com.oracle.citiccloud.api.NotFoundException;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.validation.constraints.*;

/**
 * 
 * OPC无工单系统，暂不实现
 *
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-05-17T12:22:27.214+08:00")
public class WorkordersApiServiceImpl extends WorkordersApiService {
    @Override
    public Response workordersContentsGet( @NotNull String questionId, SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    @Override
    public Response workordersContentsPost(OrdersContentInsert body, SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    @Override
    public Response workordersGet( String ids,  String addStartTime,  String addEndTime,  String serviceIds,  String questionStatus,  String pageSize,  String pageStart, SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    @Override
    public Response workordersPost(Workorder workorder, SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
}
