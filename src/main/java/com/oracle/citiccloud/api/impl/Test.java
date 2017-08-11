package com.oracle.citiccloud.api.impl;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.oracle.citiccloud.api.NotFoundException;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Test json = new Test();
		JSONObject jObj = json.jsonDataTest();

		ServiceInstancesApiServiceImpl sas = new ServiceInstancesApiServiceImpl();
		try {
			//sas.serviceInstancesInstanceIdPut("test_instanceId", jObj, null);
			sas.serviceInstancesInstanceIdPost("test_instanceId", jObj, null);
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	// 测试用JSON对象
		private JSONObject jsonDataTest() {
			JSONObject jsonObject = new JSONObject();
			//jsonObject.put("instance_id", "cffe8d3c-7628-4378-a07f-a7bf6c3871a1");
			//jsonObject.put("service_id", "dbaas");
			//jsonObject.put("org_id", "oracle");
			jsonObject.put("operation", "update");
			jsonObject.put("accepts_incomplete", true);
			
			JSONObject parameters = new JSONObject();
			jsonObject.put("parameters", parameters);
				parameters.put("description","xample service instance");
				parameters.put("edition","EE_HP");
				parameters.put("level","PAAS");
				parameters.put("serviceName","test111245");
				parameters.put("shape","oc3");
				parameters.put("subscriptionType", "MONTHLY");
				parameters.put("version","12.1.0.2");
				parameters.put("vmPublicKeyText","ssh-rsa AAAAB3NzaC1yc2EAAAABJQAAAQEAgkGh9AeUIQOY5Fh9UgfuH+4AnIRzSIR72iIXJ420baSV6HtlDczwL+79QclzwjRA0ACK8fXV94QxEUFfc6PlHULqRg0WUd3SmkuR8jYOwzl1nj5l3RoSpQlt1hWMXCkXx4JC0QrSHvlrjgdb73EnPQCWx3LGwMxpvTJCH5sUSb9rP73q4KNOH6nSVEd9oWv0usioFsy1qZg5Ue3OHIuwH/qHtgt/7zNgmcKmk9c+TWOY32PfnP2HC6uED92X867/poKmf0mH7JgvRByzHzXr2huYTCGWk8MAUxLEdrN3yqeWkx57OVShYKatZHtJpAOgHBiKkH9rn+aiz2bU/6thLQ== myOracleKey0602");

				JSONArray  jsonArray = new JSONArray ();
				JSONObject o_parameters = new JSONObject();
				jsonArray.add(o_parameters);
				parameters.put("parameters", jsonArray);				
					o_parameters.put("type","db"); // 必填
					o_parameters.put("usableStorage","15");// 必填
					o_parameters.put("adminPassword","Welcome_1");// 必填
					o_parameters.put("sid","ORCL");// 必填
					o_parameters.put("pdbName","MYPDB");
					o_parameters.put("failoverDatabase","no");
					o_parameters.put("backupDestination","NONE");// 必填
					//o_parameters.put("cloudStorageContainer","Storage-usexample/dbcsbackup");
					//o_parameters.put("cloudStorageUser","dbaasadmin");
					//o_parameters.put("cloudStoragePwd","Pa55_word");
			
			JSONObject pricing_model = new JSONObject();
			jsonObject.put("pricing_model", pricing_model); 
				pricing_model.put("mode", "package");
				pricing_model.put("unit", "monthly");
				pricing_model.put("amount","12");
				
			return jsonObject;
		}

}
