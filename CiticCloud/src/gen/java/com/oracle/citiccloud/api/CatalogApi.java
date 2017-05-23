package com.oracle.citiccloud.api;

import com.oracle.citiccloud.model.*;
import com.oracle.citiccloud.api.CatalogApiService;
import com.oracle.citiccloud.api.factories.CatalogApiServiceFactory;

import io.swagger.annotations.ApiParam;
import io.swagger.jaxrs.*;

import com.oracle.citiccloud.model.Supplier;

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

@Path("/catalog")

@Produces({ "application/json" })
@io.swagger.annotations.Api(description = "the catalog API")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-05-17T12:22:27.214+08:00")
public class CatalogApi  {
   private final CatalogApiService delegate = CatalogApiServiceFactory.getCatalogApi();

    @GET
    
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "获取服务目录", notes = "服务目录是一个适配器必须实现的第一个接入点。在初始化时，中信云平台将从所有适配器中读取供应商及其服务信息，并调整用户看到的服务目录。如果服务目录没法初始化或无效，中信云平台将不允许运营管理员注册这一适配器，并将给出具体原因。当适配器更新时，平台将更新响应的服务目录，管理员可以通过命令行中的 update-suppliers-adapter 更新供应商和服务信息。 平台从一个适配器获得服务目录信息后，会用自己存储的供应商和服务的ID unique_id 与适配器中的供应商和服务ID进行比较。如果其中任何一个不存在，则将增加新纪录。对于存在的供应商和服务ID，平台会更新对应服务目录。 如果平台里包含此适配器中没有的服务，并且这个服务没有服务实例，平台将删除此服务，如果服务有服务实例，则此服务会被标识为对所有人不可见。如果供应商不在存在服务，平台将删除此供应商.", response = Supplier.class, responseContainer = "List", tags={ "adapter-management", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "返回服务目录.", response = Supplier.class, responseContainer = "List") })
    public Response catalogGet(@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.catalogGet(securityContext);
    }
}
