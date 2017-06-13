package com.oracle.citiccloud.api.impl;

import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.mysql.jdbc.Connection;
import com.oracle.citiccloud.api.ApiResponseMessage;
import com.oracle.citiccloud.api.CloudUtil;
import com.oracle.citiccloud.api.JacksonUtil;
import com.oracle.citiccloud.api.NotFoundException;
import com.oracle.citiccloud.api.ServiceInstancesApiService;
import com.oracle.citiccloud.api.TransformUtil;
import com.oracle.citiccloud.model.DBCSInstance;
import com.oracle.citiccloud.model.Instances;
import com.oracle.citiccloud.model.ServiceInstance;
import com.oracle.citiccloud.model.ServiceInstanceModify;
import com.oracle.citiccloud.model.dbaas.DaasServiceInstance;
import com.oracle.localdbconn.DbConnection;
import com.oracle.localdbconn.LocalDbObject;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-05-17T12:22:27.214+08:00")
public class ServiceInstancesApiServiceImpl extends ServiceInstancesApiService {

	/**
	 * 返回服务实例列表 serviceId: instanceIds:
	 * 
	 */

	public final static String SERVICE_NAME_DBCS = "oracle_dbcs";
	public final static String SERVICE_NAME_JCS = "oracle_jcs";

	@Override
	public Response serviceInstancesGet(@NotNull String serviceId,
			List<String> instanceIds, SecurityContext securityContext)
			throws NotFoundException {
		if (serviceId == null || serviceId.equals("")) {
			return Response.status(Response.Status.BAD_REQUEST)
					.type(MediaType.APPLICATION_JSON).build();
		} else if (serviceId.equalsIgnoreCase("dbcs")) {
			return Response.ok().entity(this.getDBCSInstances()).build();
		} else if (serviceId.equalsIgnoreCase("jcs")) {
			return Response.ok().entity(this.getJCSInstances()).build();

		} else if (serviceId.equalsIgnoreCase("iaas")) {
			return Response.ok().entity(this.getIaaSInstances()).build();

		} else {
			return Response.status(Response.Status.BAD_REQUEST)
					.entity("incorrect serviceId")
					.type(MediaType.APPLICATION_JSON).build();
		}

	}

	/**
	 * 创建服务实例 instanceId: 由中信云平台给提供。这个ID会用来对此服务实例进行后继操作，所以适配器必须将此ID与具体的服务实例相关联
	 * body:
	 * 
	 */
	@Override
	public Response serviceInstancesInstanceIdPut(String reqInstanceId,JSONObject body, SecurityContext securityContext)
			throws NotFoundException {

		// 记录操作日志/向本地数据库插入数据
		DbConnection dbconn = new DbConnection();

		String ramdonId = dbconn.getRamdonId();// 获取随机数，为了识别不同的每次操作的
		LocalDbObject localobj = dbconn.setInsertObj(reqInstanceId, ramdonId,
				body);
		String sql = dbconn.getLocalSql().get("insertSQL");

		// 向本地数据库插入数据
		dbconn.insertData(sql, localobj);

		// 将异步操作job URI（OPC API返回json中）记录，用于查询job状态。调用OPC
		// API后，从返回的header中解析Location获取job。
		// 例如：https://dbaas.oraclecloud.com:443/paas/service/dbcs/api/v1.1/instances/midas/status/delete/job/11901471

		String serviceId = (String) body.get("service_id");

		// String serviceId = body.getServiceId();
		System.out.println("requestJSON: " + body);

		if (serviceId == null || serviceId.equals("")) {
			return Response.status(Response.Status.BAD_REQUEST)
					.type(MediaType.APPLICATION_JSON).build();
		}

		// 创建DBAAS服务实例
		else if (serviceId.equalsIgnoreCase("dbaas")) {
			
			// JSON Body 转 DBAAS对象
			DaasServiceInstance dsi = TransformUtil.mapToObject(body,
					DaasServiceInstance.class);

			return Response.ok().entity(this.createDBCSInstance(dsi, ramdonId, localobj)).build();
		}

		else if (serviceId.equalsIgnoreCase("jaas")) {
			
			return Response.ok().entity(this.createJCSInstance(body)).build();
			
		} else {
			
			return Response.status(Response.Status.BAD_REQUEST).entity("incorrect serviceId").type(MediaType.APPLICATION_JSON).build();
		}
		// return null;
		// 先返回202 ACCEPTED
		//return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
	}

	/**
	 * 变更服务实例
	 * 
	 * 
	 */
	@Override
	public Response serviceInstancesInstanceIdPost(String reqInstanceId,JSONObject body, SecurityContext securityContext)
			throws NotFoundException {

		DbConnection dbconn = new DbConnection();
		String ramdonId = dbconn.getRamdonId();// 获取随机数，为了识别不同的每次操作的
		String sql = dbconn.getLocalSql().get("selectSQL") + " '" + reqInstanceId +"' ";
		String oprationType = (String) body.get("operation");
		// 根据SeriviceId 查询最新的数据
		ResultSet rs = dbconn.selectData(sql);
		LocalDbObject localobj = null;
		try {
			while (rs.next()) {
				localobj = dbconn.setInsertRepObj(rs,ramdonId,oprationType);
				  }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		// 记录操作日志/向本地数据库插入数据
		String insertSql = dbconn.getLocalSql().get("insertSQL");

		// 向本地数据库插入数据
		dbconn.insertData(insertSql, localobj);

		// 先查看该instance是否有未完成任务

		return Response
				.ok()
				.entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!"))
				.build();
	}

	/**
	 * 删除服务实例
	 * 
	 * 
	 */
	@Override
	public Response serviceInstancesInstanceIdDelete(String instanceId,
			@NotNull Boolean acceptsIncomplete, SecurityContext securityContext)
			throws NotFoundException {
		// 记录操作日志

		// DBCS
		String domain = "midas";
		String baseUrl = "https://dbaas.oraclecloud.com/paas/service/dbcs/api/v1.1/instances/";
		String username = "duanhui@midas.site";
		String password = "CiticC1oud@orc1";

		String usernameAndPassword = username + ":" + password;
		String authorizationHeaderName = "Authorization";
		String authorizationHeaderValue = "Basic "
				+ java.util.Base64.getEncoder().encodeToString(
						usernameAndPassword.getBytes());

		Client client = ClientBuilder.newClient();
		WebTarget myResource = client.target(baseUrl + domain);

		// TODO: 如果获取超时需处理
		String response = myResource.request()
				.header(authorizationHeaderName, authorizationHeaderValue)
				.header("X-ID-TENANT-NAME", domain).get(String.class);

		return Response
				.ok()
				.entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!"))
				.build();
	}

	/**
	 * 获取正在进行的上一个操作
	 * 
	 */
	@Override
	public Response serviceInstancesInstanceIdLastOperationGet(
			String instanceId, SecurityContext securityContext)
			throws NotFoundException {
		// TODO：通过instanceId查询job URI，根据job URI查job status

		return Response
				.ok()
				.entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!"))
				.build();
	}

	/**
	 * 可选接口，暂不实现。
	 */
	@Override
	public Response serviceInstancesVerifyPut(ServiceInstance instance,
			SecurityContext securityContext) throws NotFoundException {
		// do some magic!
		return Response
				.ok()
				.entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!"))
				.build();
	}

	private List<Instances> getDBCSInstances() {
		// TODO: 配置文件或者数据库中读取
		CloudUtil.auth();

		String domain = "cnzhongxin";
		String baseUrl = "https://dbaas.oraclecloud.com/paas/service/dbcs/api/v1.1/instances/";
		String username = "wanghl6@citic.com";
		String password = "Zhongxin123!";

		String usernameAndPassword = username + ":" + password;
		String authorizationHeaderName = "Authorization";
		String authorizationHeaderValue = "Basic "
				+ java.util.Base64.getEncoder().encodeToString(
						usernameAndPassword.getBytes());

		Client client = ClientBuilder.newClient();
		WebTarget myResource = client.target(baseUrl + domain);
		// TODO: 如果获取超时需处理
		String response = myResource.request()
				.header(authorizationHeaderName, authorizationHeaderValue)
				.header("X-ID-TENANT-NAME", domain).get(String.class);

		System.out.println("response: " + response);

		JsonObject obj = new JsonParser().parse(response).getAsJsonObject();
		System.out.println("service_type:" + obj.get("service_type"));

		// Services Array
		JsonArray array = obj.getAsJsonArray("services");
		List<DBCSInstance> dList = new ArrayList<DBCSInstance>();

		for (int i = 0; i < array.size(); i++) {
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
		String authorizationHeaderValue = "Basic "
				+ java.util.Base64.getEncoder().encodeToString(
						usernameAndPassword.getBytes());

		Client client = ClientBuilder.newClient();
		WebTarget myResource = client.target(di.getService_uri());

		// TODO: 如果获取超时需处理
		String response = myResource.request()
				.header(authorizationHeaderName, authorizationHeaderValue)
				.header("X-ID-TENANT-NAME", domain).get(String.class);

		System.out.println("Single DBCS Instance response: \n" + response);

		// transform()
		Instances ins = TransformUtil.targetDBCSInstance(response);

		return ins;
	}

	private List<Instances> getJCSInstances() {

		List<Instances> list = new ArrayList<Instances>();

		return list;
	}

	private List<Instances> getIaaSInstances() {

		String authUrl = "https://api-Z54.compute.us6.oraclecloud.com/authenticate/";
		Client client = ClientBuilder.newClient();
		WebTarget auth = client.target(authUrl);

		// get cookie first
		Response clientResponse = auth
				.request(MediaType.TEXT_PLAIN)
				.header("Content-Type", "application/oracle-compute-v3+json")
				.post(Entity
						.json("{\"user\":\"/Compute-midas/duanhui@midas.site\",\"password\":\"CiticC1oud@orc1\"}"));

		System.out.println("status: " + clientResponse.getStatus());
		System.out.println("cookie: "
				+ clientResponse.getHeaderString("Set-Cookie"));
		System.out.println("Expires: "
				+ clientResponse.getHeaderString("Expires"));

		String cookieStr = clientResponse.getHeaderString("Set-Cookie");
		String nimbula = cookieStr.split(";")[0].substring(8);

		Cookie myCookie = new Cookie("nimbula", nimbula, "/", "");

		String url = "https://api-Z54.compute.us6.oraclecloud.com/instance/Compute-midas/duanhui@midas.site/";

		WebTarget myResource = client.target(url);
		String response = myResource.request(MediaType.TEXT_PLAIN)
				.header("Accept", "application/oracle-compute-v3+json")
				.cookie(myCookie).get(String.class);

		System.out.println("response: " + response);

		List<Instances> list = new ArrayList<Instances>();

		// parse response, add to list

		return list;
	}

	/**
	 * 创建DBAAS服务实例
	 * 
	 * 
	 */
	private JSONObject createDBCSInstance(DaasServiceInstance body,
			String ramdonId, LocalDbObject localobj) {

		// TODO: 配置文件或者数据库中读取
		DbConnection dbCon = new DbConnection();
		HashMap<String, String> daasInfo = dbCon.getOracleCloudAccInfo();

		String domain = daasInfo.get("domain");
		String baseUrl = daasInfo.get("baseUrl");
		String username = daasInfo.get("username");
		String password = daasInfo.get("password");

		String usernameAndPassword = username + ":" + password;
		String authorizationHeaderName = "Authorization";
		String authorizationHeaderValue = "Basic "
				+ java.util.Base64.getEncoder().encodeToString(
						usernameAndPassword.getBytes());

		Client client = ClientBuilder.newClient();
		WebTarget myResource = client.target(baseUrl + domain);

		// TODO: 如果获取超时需处理
		Response response = myResource.request()
				.header(authorizationHeaderName, authorizationHeaderValue)
				.header("X-ID-TENANT-NAME", domain)
				.header("Content-Type", "application/json")
				.post(Entity.json(body.getParameters()));

		String responseStr = response.readEntity(String.class);

		// 根据返回值更新Local数据库
		localobj.setJobId(response.getLocation().toString());
		localobj.setServiceUri(baseUrl + domain + "/"
				+ body.getParameters().getServiceName());
		localobj.setRep_status(String.valueOf(response.getStatus()));
		localobj.setRep_CreateTime(getOracleTimestamp(response.getDate()));
		localobj.setRep_LastModifiedTime(getOracleTimestamp(response
				.getLastModified()));
		localobj.setReq_UpdateTime(localobj.getReq_UpdateTime());

		String sql = dbCon.getLocalSql().get("updateSQLByInsertReply");
		// 向本地数据库更新数据
		dbCon.updateSQLByInsertReply(sql, localobj);

		Map result = new HashMap();
		result.put("status", String.valueOf(response.getStatus()));
		JSONObject obj = new JSONObject(result);
		return obj;
	}

	// 转换成TimeStamp
	private Timestamp getOracleTimestamp(Object value) {
		try {
			Class clz = value.getClass();
			Method method = clz.getMethod("dateValue", null);
			return (Timestamp) method.invoke(value, null);
		} catch (Exception e) {
			return null;
		}
	}

	private JSONObject createJCSInstance(JSONObject body) {
		// TODO Auto-generated method stub
		return null;
	}

}
