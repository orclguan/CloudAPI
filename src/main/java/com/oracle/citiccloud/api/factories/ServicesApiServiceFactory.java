package com.oracle.citiccloud.api.factories;

import com.oracle.citiccloud.api.ServicesApiService;
import com.oracle.citiccloud.api.impl.ServicesApiServiceImpl;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-05-17T12:22:27.214+08:00")
public class ServicesApiServiceFactory {
    private final static ServicesApiService service = new ServicesApiServiceImpl();

    public static ServicesApiService getServicesApi() {
        return service;
    }
}
