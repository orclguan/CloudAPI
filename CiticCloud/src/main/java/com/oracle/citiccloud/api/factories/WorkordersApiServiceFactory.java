package com.oracle.citiccloud.api.factories;

import com.oracle.citiccloud.api.WorkordersApiService;
import com.oracle.citiccloud.api.impl.WorkordersApiServiceImpl;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-05-17T12:22:27.214+08:00")
public class WorkordersApiServiceFactory {
    private final static WorkordersApiService service = new WorkordersApiServiceImpl();

    public static WorkordersApiService getWorkordersApi() {
        return service;
    }
}
