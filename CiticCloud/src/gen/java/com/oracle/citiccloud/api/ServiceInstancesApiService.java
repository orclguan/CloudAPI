package com.oracle.citiccloud.api;

import com.oracle.citiccloud.api.*;
import com.oracle.citiccloud.model.*;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

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

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.validation.constraints.*;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-05-17T12:22:27.214+08:00")
public abstract class ServiceInstancesApiService {
    public abstract Response serviceInstancesGet( @NotNull String serviceId, List<String> instanceIds,SecurityContext securityContext) throws NotFoundException;
    public abstract Response serviceInstancesInstanceIdDelete(String instanceId, @NotNull Boolean acceptsIncomplete,SecurityContext securityContext) throws NotFoundException;
    public abstract Response serviceInstancesInstanceIdLastOperationGet(String instanceId,SecurityContext securityContext) throws NotFoundException;
    public abstract Response serviceInstancesInstanceIdPost(String instanceId,ServiceInstanceModify body,SecurityContext securityContext) throws NotFoundException;
    public abstract Response serviceInstancesInstanceIdPut(String instanceId,ServiceInstance body,SecurityContext securityContext) throws NotFoundException;
    public abstract Response serviceInstancesVerifyPut(ServiceInstance instance,SecurityContext securityContext) throws NotFoundException;
}
