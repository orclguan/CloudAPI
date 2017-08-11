package com.oracle.citiccloud.api;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oracle.citiccloud.model.Catalog;
import com.oracle.citiccloud.model.Instances;
import com.oracle.citiccloud.model.ModelConfiguration;
import com.oracle.citiccloud.model.Operations;
import com.oracle.citiccloud.model.dbaas.resp.DbaasView;
import com.oracle.localdbconn.DbConnection;
import com.oracle.citiccloud.model.Supplier;
import com.oracle.citiccloud.model.Service;


public final class TransformUtil {

	public final static String SERVICE_NAME_DBCS = "dbaas";
	public final static String SERVICE_NAME_JCS = "jaas";

	public static void main(String[] args){

		Catalog c = getCatalog();

		System.out.println(c.getSuppliers().get(0).getName());

	}
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

			// 读取markdown
			if(catalog.getSuppliers() != null){
				if(catalog.getSuppliers().size() > 0) {
					Supplier spl = catalog.getSuppliers().get(0);
					List<Service> svcList = spl.getServices();

					for (int i = 0; i < svcList.size(); i++) {
						Service svc = svcList.get(i);
						String md = readJsonFile(svc.getName() + ".md");
						svc.getMetadata().setMarkdownDesc(md);
					}
				}
			}
		}

		return catalog;
	}
	
	/**
	 * 转换为中信云服务实例格式
	 * @param dbcs
	 * @return
	 */
	public static Instances targetDBCSInstance(DbaasView dbcs, String serviceId) {
		Instances ins = new Instances();
		String operationsJson = readJsonFile("operations_dbcs.json");
		List<Operations> operationList = mapToObject(operationsJson, new TypeReference<List<Operations>>(){});

		String configurationsJson = readJsonFile("configurations_dbcs.json");
		List<ModelConfiguration> configurations = mapToObject(
			configurationsJson, new TypeReference<List<ModelConfiguration>>(){});


		for (ModelConfiguration config : configurations) {
			try {
				Field field = dbcs.getClass().getDeclaredField(config.getName());
				field.setAccessible(true);

				if (field != null && field.get(dbcs) != null) {
					config.setValue(field.get(dbcs).toString());
					config.setKey(field.get(dbcs).toString());

					System.out.println(field.getName() + "=" + field.get(dbcs));
				}
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			}
		}

		String instanceId = "";

		try {
			DbConnection conn = new DbConnection();

			String selectSQL =
					"SELECT MAX(t.req_instanceId) req_instanceId FROM co_operation t "
					+ "WHERE t.instanceId = '" + dbcs.getService_name() + "';";

			ResultSet rs = conn.selectData(selectSQL);
			
			if (rs.next()) {
				instanceId = rs.getString("req_instanceId");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		ins.setId(instanceId); // instance_id DB中读取
		ins.setServiceId(serviceId);
		ins.setConfigurations(configurations);//现有的Operation不需要参数配置, 若匹配需传入catalog>service>configuratoin_options
		ins.setOperations(operationList);

		return ins;
	}

	public static <T> T mapToObject(JSONObject json, Class<T> T) {
		json.toJSONString();
		return mapToObject(json.toJSONString(), T);
	}

	public static <T> T mapToObject(JSONObject json, TypeReference<T> typeRef) {
		json.toJSONString();
		return mapToObject(json.toJSONString(), typeRef);
	}

	public static <T> T mapToObject(String jsonStr, Class<T> T) {
		T obj = null;
		try {
			obj = mapper.readValue(jsonStr, T);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return obj;
	}

	public static <T> T mapToObject(String jsonStr, TypeReference<T> typeRef) {
		T obj = null;
		try {
			obj = mapper.readValue(jsonStr, typeRef);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return obj;
	}

	public static JSONObject mapToJson(Object model) {
		String jsonStr = mapToJsonStr(model);
		JSONParser parser = new JSONParser();
		JSONObject json = new JSONObject();
		try {
			json = (JSONObject) parser.parse(jsonStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return json;
	}

	public static String mapToJsonStr(Object model) {
		String jsonStr = "";
		try {
			jsonStr = mapper.disableDefaultTyping().writeValueAsString(model);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonStr;
	}

	public static String readJsonFile(String fileName) {
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
