package com.oracle.citiccloud.api.factories;

import com.oracle.citiccloud.api.ServiceInstancesApiService;
import com.oracle.citiccloud.api.impl.ServiceInstancesApiServiceImpl;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-05-17T12:22:27.214+08:00")
public class ServiceInstancesApiServiceFactory {
    private final static ServiceInstancesApiService service = new ServiceInstancesApiServiceImpl();

    public static ServiceInstancesApiService getServiceInstancesApi() {
        return service;
    }
}
