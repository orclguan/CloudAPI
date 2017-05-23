package com.oracle.citiccloud.api.impl;

import com.oracle.citiccloud.api.*;

import com.oracle.citiccloud.model.Supplier;

import com.oracle.citiccloud.api.NotFoundException;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-05-17T12:22:27.214+08:00")
public class CatalogApiServiceImpl extends CatalogApiService {
    
	/**
	 * 获取供应商信息及服务目录
	 * 
	 */
	@Override
    public Response catalogGet(SecurityContext securityContext) throws NotFoundException {
        
    	Supplier spl = new Supplier();
    	spl.setId("orcl");
    	spl.setName("Oracle Cloud");
    	
    	//添加其他供应商信息
    	
    	//添加供应商提供的服务
    	
    	
        return Response.ok().entity(spl).build();
    }
}
