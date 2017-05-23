package com.oracle.citiccloud.api.factories;

import com.oracle.citiccloud.api.CatalogApiService;
import com.oracle.citiccloud.api.impl.CatalogApiServiceImpl;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-05-17T12:22:27.214+08:00")
public class CatalogApiServiceFactory {
    private final static CatalogApiService service = new CatalogApiServiceImpl();

    public static CatalogApiService getCatalogApi() {
        return service;
    }
}
