package com.oracle.citiccloud.api.impl;

import com.oracle.citiccloud.api.*;

import com.oracle.citiccloud.api.NotFoundException;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.validation.constraints.*;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-05-17T12:22:27.214+08:00")
public class SuppliersServiceApiServiceImpl extends SuppliersServiceApiService {
    
	/**
	 * 收支明细
	 */
	@Override
    public Response suppliersServiceBillDetailGet( @NotNull String startDate,  @NotNull String endDate, SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
	
	
	/**
	 * 消费明细
	 */
    @Override
    public Response suppliersServiceConsumeDetailGet( @NotNull String startDate,  @NotNull String endDate, SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    
    /**
     * 账单查询
     */
    @Override
    public Response suppliersServiceTotalBillGet( @NotNull String startDate,  @NotNull String endDate,  @NotNull String supplierId,  @NotNull String grainSize, SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    
    /**
     * 使用记录
     */
    @Override
    public Response suppliersServiceUsageRecordGet( @NotNull String startDate,  @NotNull String endDate,  @NotNull String serviceId,  @NotNull String grainSize, SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
}
