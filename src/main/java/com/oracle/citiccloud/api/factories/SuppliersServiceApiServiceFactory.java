package com.oracle.citiccloud.api.factories;

import com.oracle.citiccloud.api.SuppliersServiceApiService;
import com.oracle.citiccloud.api.impl.SuppliersServiceApiServiceImpl;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-05-17T12:22:27.214+08:00")
public class SuppliersServiceApiServiceFactory {
    private final static SuppliersServiceApiService service = new SuppliersServiceApiServiceImpl();

    public static SuppliersServiceApiService getSuppliersServiceApi() {
        return service;
    }
}
