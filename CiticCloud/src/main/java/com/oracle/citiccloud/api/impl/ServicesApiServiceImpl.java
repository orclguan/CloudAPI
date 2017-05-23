package com.oracle.citiccloud.api.impl;

import com.oracle.citiccloud.api.*;

import com.oracle.citiccloud.api.NotFoundException;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.validation.constraints.*;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-05-17T12:22:27.214+08:00")
public class ServicesApiServiceImpl extends ServicesApiService {
    
	/**
	 * 可选接口，暂不实现。
	 */
	@Override
    public Response servicesServiceIdGet(String serviceId,  @NotNull String orgId, SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
}
