package com.oracle.citiccloud.api;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oracle.citiccloud.model.Catalog;
import com.oracle.citiccloud.model.DBCSInstance;
import com.oracle.citiccloud.model.Instances;
import com.oracle.citiccloud.model.ModelConfiguration;
import com.oracle.citiccloud.model.Operations;
import com.oracle.citiccloud.model.Service;

public final class TransformUtil {

	public final static String SERVICE_NAME_DBCS = "oracle_dbcs";
	public final static String SERVICE_NAME_JCS = "oracle_jcs";

	public static Catalog getCatalog() {
		if (catalog == null) {
			catalog = new Catalog();
			//读取本地Catalog信息
			String supplierJson = readJsonFile("supplier.json");
			try {
				//JSON映射
				catalog = mapper.readValue(supplierJson, Catalog.class);
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
		}

		return catalog;
	}

	public static Instances targetDBCSInstance(String dbcsJson) {
		Instances ins = new Instances();
		DBCSInstance dbcs = mapToObject(dbcsJson, DBCSInstance.class);

		String operationsJson = readJsonFile("operations_dbcs.json");
		//operationList = mapper.readValue(operationsJson, List.class);
		List<Operations> operationList = mapToObject(operationsJson, new TypeReference<List<Operations>>(){});

		String configurationsJson = readJsonFile("configurations_dbcs.json");
		List<ModelConfiguration> configurations = mapToObject(
			configurationsJson, new TypeReference<List<ModelConfiguration>>(){});

		List<Service> serviceList = getCatalog().getSuppliers().get(0).getServices();

		for (Service s : serviceList) {
			if (SERVICE_NAME_DBCS.equals(s.getName())) {
				ins.setServiceId(s.getId());
			}
		}

		ins.setId(""); // instance_id DB中读取？
		//ins.setServiceId("");
		ins.setConfigurations(configurations);
		ins.setOperations(operationList);

		return ins;
	}

	public static <T> T mapToObject(String json, Class<T> T) {
		T obj = null;
		try {
			obj = mapper.readValue(json, T);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return obj;
	}

	public static <T> T mapToObject(String json, TypeReference<T> typeRef) {
		T obj = null;
		try {
			obj = mapper.readValue(json, typeRef);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return obj;
	}

	public static String mapToJson(Object model) {
		String json = "";
		try {
			json = mapper.disableDefaultTyping().writeValueAsString(model);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return json;
	}

	private static String readJsonFile(String fileName) {
		ClassLoader classLoader = TransformUtil.class.getClassLoader();
		File file = new File(classLoader.getResource(fileName).getFile());
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

	private static ObjectMapper mapper = new ObjectMapper();
	private static Catalog catalog = null;

}
