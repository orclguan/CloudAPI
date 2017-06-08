package com.oracle.citiccloud.api.impl;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.json.simple.JSONObject;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.oracle.citiccloud.api.ApiResponseMessage;
import com.oracle.citiccloud.api.CloudUtil;
import com.oracle.citiccloud.api.NotFoundException;
import com.oracle.citiccloud.api.ServiceInstancesApiService;
import com.oracle.citiccloud.api.TransformUtil;
import com.oracle.citiccloud.model.DBCSInstance;
import com.oracle.citiccloud.model.Instances;
import com.oracle.citiccloud.model.ServiceInstance;
import com.oracle.citiccloud.model.ServiceInstanceModify;
import com.oracle.citiccloud.model.dbaas.DaasServiceInstance;


@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-05-17T12:22:27.214+08:00")
public class ServiceInstancesApiServiceImpl extends ServiceInstancesApiService {
    
	/**
	 * 返回服务实例列表
	 * serviceId: 
	 * instanceIds: 
	 * 
	 */

	public final static String SERVICE_NAME_DBCS = "oracle_dbcs";
	public final static String SERVICE_NAME_JCS = "oracle_jcs";

	@Override
    public Response serviceInstancesGet( @NotNull String serviceId,  List<String> instanceIds, SecurityContext securityContext) throws NotFoundException {
		if (serviceId==null || serviceId.equals("")){
			return Response.status(Response.Status.BAD_REQUEST).type(MediaType.APPLICATION_JSON).build();
		}else if(serviceId.equalsIgnoreCase("dbcs")){
			return Response.ok().entity(this.getDBCSInstances()).build();
		}else if(serviceId.equalsIgnoreCase("jcs")){
			return Response.ok().entity(this.getJCSInstances()).build();
			
		}else if(serviceId.equalsIgnoreCase("iaas")){
			return Response.ok().entity(this.getIaaSInstances()).build();
			
		}else{
			return Response.status(Response.Status.BAD_REQUEST).entity("incorrect serviceId").type(MediaType.APPLICATION_JSON).build();
		}
        
    }
	
	/**
     * 创建服务实例
     * instanceId: 由中信云平台给提供。这个ID会用来对此服务实例进行后继操作，所以适配器必须将此ID与具体的服务实例相关联
     * body: 
     * 
     */
    @Override
    public Response serviceInstancesInstanceIdPut(String instanceId, JSONObject body, SecurityContext securityContext) throws NotFoundException {
		
		//记录操作日志
		
		//将异步操作job URI（OPC API返回json中）记录，用于查询job状态。调用OPC API后，从返回的header中解析Location获取job。例如：https://dbaas.oraclecloud.com:443/paas/service/dbcs/api/v1.1/instances/midas/status/delete/job/11901471
		
    	    	
    	String serviceId = (String) body.get("service_id");
    	System.out.println(serviceId);  	
		//String serviceId = body.getServiceId();		
    	
		if (serviceId==null || serviceId.equals("")){
			
			return Response.status(Response.Status.BAD_REQUEST).type(MediaType.APPLICATION_JSON).build();	
			
		}else if(serviceId.equalsIgnoreCase("dbaas")){
			//JSON Body 转 DBAAS对象

	    	System.out.println("bbb:"+body);	
			DaasServiceInstance dsi ;// = DaasServiceInstance.class.newInstance();
			
			dsi =  TransformUtil.mapToObject(body, DaasServiceInstance.class);

		    	System.out.println("aaaa:"+dsi.getService_id());
				return Response.ok().entity(this.createDBCSInstance(dsi)).build();	
			
			
			
		}else if(serviceId.equalsIgnoreCase("jaas")){
			
			return null;
			//return Response.ok().entity(this.createJCSInstance(body)).build();
			
		}else{
			
			return Response.status(Response.Status.BAD_REQUEST).entity("incorrect serviceId").type(MediaType.APPLICATION_JSON).build();
			
		}
		//return null;
    	
    	//先返回202 ACCEPTED
    	
        //return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    
	/**
     * 变更服务实例
     * 
     * 
     */
    @Override
    public Response serviceInstancesInstanceIdPost(String instanceId, ServiceInstanceModify body, SecurityContext securityContext) throws NotFoundException {
    	//记录操作日志
    	
    	//先查看该instance是否有未完成任务
    	
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    
	/**
	 * 删除服务实例
	 * 
	 * 
	 */
    @Override
    public Response serviceInstancesInstanceIdDelete(String instanceId,  @NotNull Boolean acceptsIncomplete, SecurityContext securityContext) throws NotFoundException {
    	//记录操作日志
    	
    	// DBCS
    	String domain = "midas";
		String baseUrl = "https://dbaas.oraclecloud.com/paas/service/dbcs/api/v1.1/instances/";
		String username = "duanhui@midas.site";
		String password = "CiticC1oud@orc1";
		
		String usernameAndPassword = username + ":" + password;
		String authorizationHeaderName = "Authorization";
		String authorizationHeaderValue = "Basic " + java.util.Base64.getEncoder().encodeToString( usernameAndPassword.getBytes() );
		
		Client client = ClientBuilder.newClient();
		WebTarget myResource = client.target(baseUrl + domain);
		
		//TODO: 如果获取超时需处理
		String response = myResource.request()
				.header( authorizationHeaderName, authorizationHeaderValue )
				.header("X-ID-TENANT-NAME", domain)
				.get(String.class);
		
    	return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    
    
	/**
	 * 获取正在进行的上一个操作
	 * 
	 */
    @Override
    public Response serviceInstancesInstanceIdLastOperationGet(String instanceId, SecurityContext securityContext) throws NotFoundException {
        // TODO：通过instanceId查询job URI，根据job URI查job status
    	
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    
    /**
     * 可选接口，暂不实现。
     */
    @Override
    public Response serviceInstancesVerifyPut(ServiceInstance instance, SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    
	
	private List<Instances> getDBCSInstances(){
		//TODO: 配置文件或者数据库中读取
		CloudUtil.auth();
		
		
		String domain = "cnzhongxin";
		String baseUrl = "https://dbaas.oraclecloud.com/paas/service/dbcs/api/v1.1/instances/";
		String username = "wanghl6@citic.com";
		String password = "Zhongxin123!";
		
		String usernameAndPassword = username + ":" + password;
		String authorizationHeaderName = "Authorization";
		String authorizationHeaderValue = "Basic " + java.util.Base64.getEncoder().encodeToString( usernameAndPassword.getBytes() );

		Client client = ClientBuilder.newClient();
		WebTarget myResource = client.target(baseUrl + domain);
		//TODO: 如果获取超时需处理
		String response = myResource.request()
				.header( authorizationHeaderName, authorizationHeaderValue )
				.header("X-ID-TENANT-NAME", domain)
				.get(String.class);

		System.out.println("response: " + response);
		
		JsonObject obj = new JsonParser().parse(response).getAsJsonObject();
		System.out.println("service_type:" + obj.get("service_type"));

		//Services Array
		JsonArray array = obj.getAsJsonArray("services");
		List<DBCSInstance> dList = new ArrayList<DBCSInstance>();

		for (int i = 0; i < array.size(); i++){
			JsonObject o = (JsonObject) array.get(i);
			DBCSInstance di = new DBCSInstance();
			System.out.println("service_name:" + o.get("service_name"));
			System.out.println("service_uri:" + o.get("service_uri"));

			di.setService_uri(o.get("service_uri").getAsString());
			dList.add(di);
		}

		List<Instances> list = new ArrayList<Instances>();
		for (DBCSInstance dbcs : dList) {
			list.add(getSingleDBCSInstance(dbcs));
		}

		return list;
	}

	private Instances getSingleDBCSInstance(DBCSInstance di) {
		String domain = "cnzhongxin";
		String username = "wanghl6@citic.com";
		String password = "Zhongxin123!";

		String usernameAndPassword = username + ":" + password;
		String authorizationHeaderName = "Authorization";
		String authorizationHeaderValue = "Basic " + java.util.Base64.getEncoder().encodeToString( usernameAndPassword.getBytes() );

		Client client = ClientBuilder.newClient();
		WebTarget myResource = client.target(di.getService_uri());

		//TODO: 如果获取超时需处理
		String response = myResource.request()
				.header( authorizationHeaderName, authorizationHeaderValue )
				.header("X-ID-TENANT-NAME", domain)
				.get(String.class);

		System.out.println("Single DBCS Instance response: \n" + response);

		//transform()
		Instances ins = TransformUtil.targetDBCSInstance(response);

		return ins;
	}

	private List<Instances> getJCSInstances(){
		
		List<Instances> list = new ArrayList<Instances>();
		
		
		return list;
	}
	
	
	private List<Instances> getIaaSInstances(){
		
		String authUrl = "https://api-Z54.compute.us6.oraclecloud.com/authenticate/";
		Client client = ClientBuilder.newClient();
		WebTarget auth = client.target(authUrl);
		
		// get cookie first
		Response clientResponse = auth.request(MediaType.TEXT_PLAIN)
				.header("Content-Type", "application/oracle-compute-v3+json")
				.post(Entity.json("{\"user\":\"/Compute-midas/duanhui@midas.site\",\"password\":\"CiticC1oud@orc1\"}"));
		
		System.out.println("status: " + clientResponse.getStatus());
		System.out.println("cookie: " + clientResponse.getHeaderString("Set-Cookie"));
		System.out.println("Expires: " + clientResponse.getHeaderString("Expires"));
		
		String cookieStr = clientResponse.getHeaderString("Set-Cookie");
		String nimbula = cookieStr.split(";")[0].substring(8);
		
		Cookie myCookie = new Cookie("nimbula", nimbula, "/", "");
		
		String url = "https://api-Z54.compute.us6.oraclecloud.com/instance/Compute-midas/duanhui@midas.site/";
		
		WebTarget myResource = client.target(url);
		String response = myResource.request(MediaType.TEXT_PLAIN)
		        .header("Accept", "application/oracle-compute-v3+json")
		        .cookie(myCookie)
		        .get(String.class);
		
		System.out.println("response: " + response);
		
		List<Instances> list = new ArrayList<Instances>();
		
		// parse response, add to list
		
		return list;
	}
	
	private JsonObject createDBCSInstance(DaasServiceInstance body) {
		//TODO: 配置文件或者数据库中读取
		String domain = "midas";
		String baseUrl = "https://dbaas.oraclecloud.com/paas/service/dbcs/api/v1.1/instances/";
		String username = "duanhui@midas.site";
		String password = "CiticC1oud@orc1";		
		System.out.println("1111");
		
		String usernameAndPassword = username + ":" + password;
		String authorizationHeaderName = "Authorization";
		String authorizationHeaderValue = "Basic " + java.util.Base64.getEncoder().encodeToString( usernameAndPassword.getBytes() );
		
		System.out.println("2222");
		Client client = ClientBuilder.newClient();
		WebTarget myResource = client.target(baseUrl + domain);	
		System.out.println("333");
		System.out.println(body.getInstance_id());

		
		//TODO: 如果获取超时需处理
		Response response = myResource.request()
				.header( authorizationHeaderName, authorizationHeaderValue )
				.header("X-ID-TENANT-NAME", domain)
				.post(Entity.json(body));	
		System.out.println("4444");

		String responseStr = response.readEntity(String.class);	
		System.out.println("5555");

		System.out.println("response: " + responseStr);
//		{
//			  "description": "Example service instance",
//			  "edition": "EE_HP",
//			  "level": "PAAS",
//			  "serviceName": "db12c-xp-si",
//			  "shape": "oc3",
//			  "subscriptionType": "MONTHLY",
//			  "version": "12.1.0.2",
//			  "vmPublicKeyText": "ssh-rsa AAAAB3NzaC1y...",
//			  "parameters": [
//			    {
//			      "type": "db",
//			      "usableStorage": "15",
//			      "adminPassword": "Welcome_1",
//			      "sid": "ORCL",
//			      "pdbName": "MYPDB",
//			      "failoverDatabase": "no",
//			      "backupDestination": "BOTH",
//			      "cloudStorageContainer": "Storage-usexample\/dbcsbackups",
//			      "cloudStorageUser": "dbaasadmin",
//			      "cloudStoragePwd": "Pa55_word"
//			    }
//			  ]
//			}
		
		return null;
	}
	
    private JsonObject createJCSInstance(DaasServiceInstance body) {
		// TODO Auto-generated method stub
		return null;
	}
    
    //测试用JSON对象
    private JSONObject jsonDataTest(){
    	JSONObject jsonObject = new JSONObject(); 
    	jsonObject.put("instance_id", "cffe8d3c-7628-4378-a07f-a7bf6c3871a1");
    	jsonObject.put("service_id", "dbaas");
    	//jsonObject.put("accepts_incomplete", true);
    	//JSONObject jsonObject1 = new JSONObject(); 
    	//jsonObject.put("parameters", jsonObject1);
    	/*
    	jsonObject1.put("RegionID", "huabei1");
    	jsonObject1.put("ZoneId", "kyq1");
    	jsonObject1.put("HostName", "mylinux");*/
    	return jsonObject;
    }
    
    //测试
    public static void main(String[] args){
    	ServiceInstancesApiServiceImpl sas = new ServiceInstancesApiServiceImpl();
    	JSONObject jObj = sas.jsonDataTest();
    	try {
			sas.serviceInstancesInstanceIdPut("cffe8d3c-7628-4378-a07f-a7bf6c3871a1", jObj, null);
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}
