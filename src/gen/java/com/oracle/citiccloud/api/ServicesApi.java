package com.oracle.citiccloud.api;

import com.oracle.citiccloud.model.*;
import com.oracle.citiccloud.api.ServicesApiService;
import com.oracle.citiccloud.api.factories.ServicesApiServiceFactory;

import io.swagger.annotations.ApiParam;
import io.swagger.jaxrs.*;

import com.oracle.citiccloud.model.Service;

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

@Path("/services")

@Produces({ "application/json" })
@io.swagger.annotations.Api(description = "the services API")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-05-17T12:22:27.214+08:00")
public class ServicesApi  {
   private final ServicesApiService delegate = ServicesApiServiceFactory.getServicesApi();

    @GET
    @Path("/{service_id}")
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "获取用户相关服务配置GET", notes = "此接口为可选实现的接口， 只有当某个服务的配置项的内容（通常是一个 option （选择类配置）,如ECS服务的私有镜像）跟具体的用户相关时，才需要实现这个接口。 此接口返回的内容与 服务目录 /catalog 接口返回的单个服务的内容的结构相同。 ", response = Service.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "返回服务的配置详情", response = Service.class) })
    public Response servicesServiceIdGet(@ApiParam(value = "服务ID",required=true) @PathParam("service_id") String serviceId
,@ApiParam(value = "组织机构ID",required=true) @QueryParam("org_id") String orgId
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.servicesServiceIdGet(serviceId,orgId,securityContext);
    }
}
