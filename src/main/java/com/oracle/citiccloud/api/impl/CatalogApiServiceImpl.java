package com.oracle.citiccloud.api.impl;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oracle.citiccloud.api.CatalogApiService;
import com.oracle.citiccloud.api.NotFoundException;
import com.oracle.citiccloud.api.TransformUtil;
import com.oracle.citiccloud.model.Catalog;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-05-17T12:22:27.214+08:00")
public class CatalogApiServiceImpl extends CatalogApiService {
	
	/**
	 * 获取供应商信息及服务目录
	 * 
	 */
	@Override
	public Response catalogGet(SecurityContext securityContext) throws NotFoundException {
		Catalog catalog = TransformUtil.getCatalog();
		ObjectMapper OM = new ObjectMapper();
		OM.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		String Reponse = new String();
		try{
			Reponse = OM.writeValueAsString(catalog);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}

		return Response.ok().entity(Reponse).build();
	}

}
