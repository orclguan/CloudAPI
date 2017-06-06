package com.oracle.citiccloud.api.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oracle.citiccloud.api.CatalogApiService;
import com.oracle.citiccloud.api.NotFoundException;
import com.oracle.citiccloud.model.DBCSInstance;
import com.oracle.citiccloud.model.Service;
import com.oracle.citiccloud.model.Supplier;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-05-17T12:22:27.214+08:00")
public class CatalogApiServiceImpl extends CatalogApiService {
	
	/**
	 * 获取供应商信息及服务目录
	 * 
	 */
	@Override
	public Response catalogGet(SecurityContext securityContext) throws NotFoundException {
		String supplierJson = readCatalog();
		ObjectMapper mapper = new ObjectMapper();
		//添加其他供应商信息
		Supplier spl = new Supplier();
		try {
			spl = mapper.readValue(supplierJson, Supplier.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		List<Service> serviceList = spl.getServices();
		// TODO: 返回的config_options 装入model

		DBCSInstance di = DBCSInstance.getInstance();
		di.setService_uri("http://www.baidu.com/test");
		
		
		List<Supplier> suppliers = new ArrayList<Supplier>();
		suppliers.add(spl);
		JSONObject result = new JSONObject();
		result.put("Suppliers", suppliers);

		//添加供应商提供的服务
		
		return Response.ok().entity(result).build();
	}

	private String readCatalog() {
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("supplier.json").getFile());
		BufferedReader reader = null;
		String laststr = "";
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF8"));
			String tempString = null;
			while ((tempString = reader.readLine()) != null) {
				laststr = laststr + tempString;
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {  
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
		return laststr;
	}
}
