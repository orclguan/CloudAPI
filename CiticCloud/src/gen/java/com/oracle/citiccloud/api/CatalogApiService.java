package com.oracle.citiccloud.api;

import com.oracle.citiccloud.api.*;
import com.oracle.citiccloud.model.*;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import com.oracle.citiccloud.model.Supplier;

import java.util.List;
import com.oracle.citiccloud.api.NotFoundException;

import java.io.InputStream;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.validation.constraints.*;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-05-17T12:22:27.214+08:00")
public abstract class CatalogApiService {
    public abstract Response catalogGet(SecurityContext securityContext) throws NotFoundException;
}
