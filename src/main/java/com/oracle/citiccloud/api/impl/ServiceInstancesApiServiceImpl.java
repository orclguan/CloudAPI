package com.oracle.citiccloud.api.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

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

import com.fasterxml.jackson.core.type.TypeReference;
import com.oracle.citiccloud.api.ApiResponseMessage;
import com.oracle.citiccloud.api.NotFoundException;
import com.oracle.citiccloud.api.ServiceInstancesApiService;
import com.oracle.citiccloud.api.TransformUtil;
import com.oracle.citiccloud.model.Instances;
import com.oracle.citiccloud.model.Service;
import com.oracle.citiccloud.model.ServiceInstance;
import com.oracle.citiccloud.model.dbaas.DbaasCreateInstanceBody;
import com.oracle.citiccloud.model.dbaas.DbaasParameter;
import com.oracle.citiccloud.model.dbaas.DbaasadditionalParams;
import com.oracle.citiccloud.model.dbaas.resp.DbaasPatch;
import com.oracle.citiccloud.model.dbaas.resp.DbaasView;
import com.oracle.citiccloud.model.dbaas.resp.DbaasViewList;
import com.oracle.citiccloud.model.dbaas.resp.JobStatus;
import com.oracle.localdbconn.DbConnection;
import com.oracle.localdbconn.LocalDbObject;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-05-17T12:22:27.214+08:00")
public class ServiceInstancesApiServiceImpl extends ServiceInstancesApiService {

	/**
	 * 返回服务实例列表 serviceId: instanceIds:
	 * 
	 */

	public final static String SERVICE_NAME_DBCS = "dbaas";
	public final static String SERVICE_NAME_JCS = "jaas";

	@Override
	public Response serviceInstancesGet(@NotNull String serviceId,
			String instanceIds, @NotNull String orgId, SecurityContext securityContext)
			throws NotFoundException {

		String serviceName = getServiceNameById(serviceId);

		if (serviceName.equals("")) {
			return Response.status(Response.Status.BAD_REQUEST)
					.entity("{\"error\": \"incorrect serviceId\"}")
					.type(MediaType.APPLICATION_JSON).build();
		} else if (serviceName.equalsIgnoreCase(SERVICE_NAME_DBCS)) {
			return Response.ok().entity(this.getDBCSInstances(instanceIds, orgId)).build();
		} else if (serviceName.equalsIgnoreCase(SERVICE_NAME_JCS)) {
			return Response.ok().entity(this.getJCSInstances()).build();

		} else if (serviceName.equalsIgnoreCase("iaas")) {
			return Response.ok().entity(this.getIaaSInstances()).build();

		} else {
			return Response.status(Response.Status.BAD_REQUEST)
					.entity("{\"error\": \"incorrect serviceId\"}")
					.type(MediaType.APPLICATION_JSON).build();
		}

	}

	/**
	 * 创建服务实例 instanceId: 由中信云平台给提供。这个ID会用来对此服务实例进行后继操作，所以适配器必须将此ID与具体的服务实例相关联
	 * body:
	 * 
	 */
	@Override
	public Response serviceInstancesInstanceIdPut(String reqInstanceId,
			ServiceInstance body, SecurityContext securityContext)
			throws NotFoundException {

		// 记录操作日志/向本地数据库插入数据
		DbConnection dbconn = new DbConnection();

		String ramdonId = dbconn.getRamdonId();// 获取随机数，为了识别不同的每次操作的
		LocalDbObject localobj = dbconn.setInsertObj(reqInstanceId, ramdonId, body);
		String sql = dbconn.getLocalSql().get("insertSQL");

		// 向本地数据库插入数据
		dbconn.insertData(sql, localobj);

		// 将异步操作job URI（OPC API返回json中）记录，用于查询job状态。调用OPC
		// API后，从返回的header中解析Location获取job。
		// 例如：https://dbaas.oraclecloud.com:443/paas/service/dbcs/api/v1.1/instances/midas/status/delete/job/11901471

		String serviceName = getServiceNameById(body.getServiceId());

		// String serviceId = body.getServiceId();
		System.out.println("requestJSON: " + body);

		if (serviceName == null || serviceName.equals("")) {
			return Response.status(Response.Status.BAD_REQUEST)
					.type(MediaType.APPLICATION_JSON).build();
		}

		// 创建DBAAS服务实例
		else if (serviceName.equalsIgnoreCase("dbaas")) {
			return createDBCSInstance(body, ramdonId, localobj);
		}

		else if (serviceName.equalsIgnoreCase("jaas")) {

			return Response.ok().entity(this.createJCSInstance(body)).build();

		} else {

			return Response.status(Response.Status.BAD_REQUEST)
					.entity("{\"error\": \"incorrect serviceId\"}")
					.type(MediaType.APPLICATION_JSON).build();
		}
		// return null;
		// 先返回202 ACCEPTED
		// return Response.ok().entity(new
		// ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
	}

	/**
	 * 变更服务实例
	 * 
	 * 
	 */
	@Override
	public Response serviceInstancesInstanceIdPost(String reqInstanceId,
			JSONObject body, SecurityContext securityContext)
			throws NotFoundException {

		DbConnection dbconn = new DbConnection();
		String ramdonId = dbconn.getRamdonId();// 获取随机数，为了识别不同的每次操作的
		//中信传过来的operation 必须为 stop,start,restart,backup,recovery
		String operationType = (String) body.get("operation");
		// 省略传入的参数parameters
		// JSONObject reqParameters = TransformUtil.mapToJson(body.get("parameters"));
		
		// 根据SeriviceId 查询最新的数据
		ResultSet rs = dbconn.selectLastRecordByInstanceId(reqInstanceId);
		LocalDbObject localobj = null;
		String lastJobId = "";
		try {
			while (rs.next()) {
				lastJobId = rs.getString("jobId");
				localobj = dbconn.setInsertRepObj(rs, ramdonId, operationType);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// 本地无记录，返回错误信息
		if (localobj == null || lastJobId.equals("")) {
			return Response.status(Response.Status.NOT_FOUND)
					.entity("{\"error\": \"Instance or Last Job of Instance does not exist!\"}")
					.type(MediaType.APPLICATION_JSON).build();
		}

		// 记录操作日志
		String insertSql = dbconn.getLocalSql().get("insertSQL");
		// 向本地数据库插入数据
		dbconn.insertData(insertSql, localobj);

		// 根据JobId获取Job状态
		String jobStatus = queryJobStatus(lastJobId).getJob_status();
		// 没有其他JOB正常运行时，
		if (jobStatus.toUpperCase().equals("SUCCEEDED")) {
			// 执行操作
			if ("patch".equals(operationType)) {
				return applyPatches(localobj);
			}
			else {
				return operateDBCSInstance(operationType, localobj);
			}
		} else {
			// JOB有未完成任务时返回错误
			return Response.status(Response.Status.BAD_REQUEST)
					.entity("{\"error\": \"An operation is currently in progress\"}")
					.type(MediaType.APPLICATION_JSON).build();
		}
	}

	/**
	 * 删除服务实例
	 * 
	 * 
	 */
	@Override
	public Response serviceInstancesInstanceIdDelete(String reqInstanceId,
			@NotNull Boolean acceptsIncomplete, SecurityContext securityContext)
			throws NotFoundException {
		
		// 记录操作日志
		DbConnection dbconn = new DbConnection();
		String ramdonId = dbconn.getRamdonId();// 获取随机数，为了识别不同的每次操作的
		//确定操作类型
		String oprationType = "delete";
		// 根据SeriviceId 查询最新的数据
		ResultSet rs = dbconn.selectLastRecordByInstanceId(reqInstanceId);
		LocalDbObject localobj = null;
		String lastJobId = "";
		try {
			while (rs.next()) {
				lastJobId = rs.getString("jobId");
				localobj = dbconn.setInsertRepObj(rs, ramdonId, oprationType);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// 本地无记录，返回错误信息
		if (localobj == null || lastJobId.equals("")) {
			return Response.status(Response.Status.NOT_FOUND)
					.entity("{\"error\": \"Instance or Last Job of Instance does not exist!\"}")
					.type(MediaType.APPLICATION_JSON).build();
		}

		// 记录操作日志
		String insertSql = dbconn.getLocalSql().get("insertSQL");
		// 向本地数据库插入数据
		dbconn.insertData(insertSql, localobj);
		
		// 先查看该instance是否有未完成任务
		// 根据JobId获取Job状态
		String jobStatus = queryJobStatus(lastJobId).getJob_status();
		
		// 没有其他JOB正常运行时，
		if (jobStatus.toUpperCase().equals("SUCCEEDED")) {
			// 没有不正常的JOB时，执行删除操作
			return Response.status(Response.Status.ACCEPTED).entity(this.deleteDBCSInstance(ramdonId,localobj)).build();
			// Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
		} else {
			// JOB有未完成任务时返回错误
			return Response.status(Response.Status.SERVICE_UNAVAILABLE).type(MediaType.APPLICATION_JSON).build();
		}
	}

	/**
	 * 获取正在进行的上一个操作
	 * 
	 */
	@Override
	public Response serviceInstancesInstanceIdLastOperationGet(
			String reqInstanceId, SecurityContext securityContext)
			throws NotFoundException {
		// 通过reqInstanceId查询job URI，根据job URI查job status

		DbConnection dbconn = new DbConnection();

		// 根据SeriviceId 查询最新的数据
		ResultSet rs = dbconn.selectLastRecordByInstanceId(reqInstanceId);
		String jobId = "";
		String serviceName = "";
		try {
			while (rs.next()) {
				jobId = rs.getString("jobId");
				serviceName = rs.getString("instanceId");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		JSONObject resp = new JSONObject();

		// 请求的instance不存在对应job
		if (jobId == null || "".equals(jobId)) {
			resp.put("state", "failed");
			resp.put("description", "Last Operation does not exist");
			resp.put("supplier_instance_id", serviceName);

			return Response.status(Response.Status.BAD_REQUEST)
					.entity(resp)
					.type(MediaType.APPLICATION_JSON).build();
		}

		// 根据JobId获取Job状态
		JobStatus jobStatus = queryJobStatus(jobId);
		String statusCode = jobStatus.getJob_status().toLowerCase();
		
		if (!statusCode.equals("succeeded") && !statusCode.equals("failed")) {
			resp.put("state", "in progress");  // succeded， failed, in progress 外还有其他状态,如 waitingonresource
		}
		else {
			resp.put("state", statusCode);
		}

		if (jobStatus.getMessage() != null) {
			String allMsg = "";
			for (String msg : jobStatus.getMessage()) {
				allMsg = allMsg + msg + "\n";
			}
			resp.put("description", allMsg);
		}

		resp.put("supplier_instance_id", serviceName);

		if (jobStatus.getJob_end_date() != null) {
			// GMT => CST
			String gmtStr = jobStatus.getJob_end_date();
			String jobEndDate = "";
			try {
				SimpleDateFormat gmtFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss 'GMT' yyyy",Locale.US);
				SimpleDateFormat localFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				gmtFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
				localFormat.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));

				Date date = gmtFormat.parse(gmtStr);
				jobEndDate = localFormat.format(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}

			resp.put("succeeded_time", jobEndDate);
		}

		return Response
				.status(Response.Status.ACCEPTED)
				.entity(resp)
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

	private JSONObject getDBCSInstances(String instanceIds, String orgId) {
		List<Instances> list = new ArrayList<Instances>();
		JSONObject jsonResp = new JSONObject();
		jsonResp.put("instances", list);

		//根据instanceIds, orgId查询本地数据库，返回instance结果
		StringBuilder str = new StringBuilder();
		str.append("SELECT req_instanceId,instanceId,req_serviceId,instanceType,req_orgId,operationId,");
		str.append("oprationType,req_UpdateTime,jobId,serviceUri,rep_status,rep_CreateTime,rep_LastModifiedTime ");
		str.append("FROM co_operation WHERE req_orgId='");
		str.append(orgId);
		str.append("'");

		if (instanceIds != null && !instanceIds.equals("")) {
			// 限定可查询instance id为200个
			String[] idArray = instanceIds.split(",");

			if(idArray.length > 200) {
				String lastOne = idArray[199];
				int offset = lastOne.length();
				instanceIds = instanceIds.substring(0, instanceIds.indexOf(lastOne) + offset);
			}

			str.append(" AND req_instanceId IN ('");
			str.append(instanceIds.replaceAll(" ", "").replaceAll(",", "','")); // 去掉空格，添加单引号
			str.append("')");
		}

		str.append(" GROUP BY req_instanceId");

		DbConnection conn = new DbConnection();
		ResultSet rs = conn.selectData(str.toString());
		List<String> localInstances = new ArrayList<>();

		try {
			while (rs.next()) {
				localInstances.add(rs.getString("instanceId"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// 本地数据库无数据，则返回空
		if (localInstances.isEmpty()) {
			return jsonResp;
		}

		// 本地数据库不为空
		// API 请求, 获取OPC数据
		DbConnection dbCon = new DbConnection();
		HashMap<String, String> daasInfo = dbCon.getOracleCloudAccInfo();

		String domain = daasInfo.get("domain");
		String baseUrl = daasInfo.get("baseUrl");
		String response = dbCon.reqGet(baseUrl + domain);

		System.out.println("response: " + response);

		// 返回所有instance
		DbaasViewList apiResponse = TransformUtil.mapToObject(response, DbaasViewList.class);
		List<DbaasView> services = apiResponse.getServices();
		String serviceName = "";

		for (DbaasView dbcs : services) {
			//中信云:instance_id <=> Oracle DBCS:service_name
			serviceName = dbcs.getService_name();

			if (localInstances.contains(serviceName)) {
				// 获取Instance详细信息
				dbcs = getSingleDBCSInstance(dbcs);
				// 转换为中信云Instances结构
				Instances ins = TransformUtil.targetDBCSInstance(dbcs, getServiceIdByName(SERVICE_NAME_DBCS));
				list.add(ins);
			}
		}

		return jsonResp;
	}

	/**
	 * 获取instance详细信息
	 * （暂时不实现）
	 */
	private DbaasView getSingleDBCSInstance(DbaasView dbcs) {
		// API 请求
		DbConnection dbCon = new DbConnection();
		HashMap<String, String> daasInfo = dbCon.getOracleCloudAccInfo();
		String domain = daasInfo.get("domain");
		String baseUrl = daasInfo.get("baseUrl");
		String targetUrl = baseUrl + domain + "/" + dbcs.getService_name();
		String response = dbCon.reqGet(targetUrl);

		System.out.println("response: " + response);

		// 返回的json转换为对象
		dbcs = TransformUtil.mapToObject(response, DbaasView.class);

		return dbcs;
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

	// 创建DBAAS服务实例
	private Response createDBCSInstance(ServiceInstance body, String ramdonId, LocalDbObject localobj) {
		// Citic Request body => Oracle Request Body
		JSONObject citicParameters = TransformUtil.mapToJson(body.getParameters());
		DbaasCreateInstanceBody reqBody = TransformUtil.mapToObject(citicParameters, DbaasCreateInstanceBody.class);
		DbaasParameter parameters = TransformUtil.mapToObject(citicParameters, DbaasParameter.class);
		DbaasadditionalParams additionalParams = TransformUtil.mapToObject(citicParameters, DbaasadditionalParams.class);

		DbConnection dbCon = new DbConnection();
		HashMap<String, String> daasInfo = dbCon.getOracleCloudAccInfo();

		// 从配置文件中配置Storage备份账户
		// An Oracle Storage Cloud Service container is not required
		// when provisioning a Database Cloud Service - Virtual Image instance (level:BASIC)
		if (reqBody.getLevel().equals("PAAS") &&
			!parameters.getBackupDestination().equals("NONE") &&
			(parameters.getCloudStorageContainer() == null || parameters.getCloudStorageContainer() == "")) {
			
			parameters.setCloudStorageContainer(daasInfo.get("cloudStorageContainer"));
			parameters.setCloudStorageUser(daasInfo.get("cloudStorageUser"));
			parameters.setCloudStoragePwd(daasInfo.get("cloudStoragePwd"));
		}

		// 从里到外，设置变量
		parameters.setAdditionalParams(additionalParams);
		reqBody.getParameters().add(parameters);

		// API 请求
		String domain = daasInfo.get("domain");
		String baseUrl = daasInfo.get("baseUrl");
		String targetUrl = baseUrl + domain;

		JSONObject reqBodyJson = TransformUtil.mapToJson(reqBody);
		Response response = dbCon.reqPost(reqBodyJson, targetUrl);

		String responseStr = response.readEntity(String.class);
		String contentType = response.getHeaderString("content-type");

		System.out.println(">>>>>> Create DBCSInstance Response:\n" + response);
		System.out.println(">>>>>> Response Body:\n" + responseStr);

		// 根据返回值更新Local数据库
		if (response.getStatus() !=  202) {
			localobj.setJobId("");
		}
		else {
			localobj.setJobId(response.getLocation().toString()); //操作成功202， header中返回job id
		}

		localobj.setServiceUri(baseUrl + domain + "/" + reqBody.getServiceName());
		localobj.setRep_status(String.valueOf(response.getStatus()));
		localobj.setRep_CreateTime(getOracleTimestamp(response.getDate()));
		localobj.setRep_LastModifiedTime(getOracleTimestamp(response.getLastModified()));
		localobj.setReq_UpdateTime(localobj.getReq_UpdateTime());

		String sql = dbCon.getLocalSql().get("updateSQLByInsertReply");
		// 向本地数据库更新数据
		dbCon.updateSQLByInsertReply(sql, localobj);

		return Response.status(response.getStatus()).entity(responseStr).type(contentType).build();
	}

	// 查询JOB状态
	private JobStatus queryJobStatus(String jobId) {
		// API 请求
		DbConnection dbCon = new DbConnection();
		String response = dbCon.reqGet(jobId);
		// 返回Job状态
		JobStatus jobStatus = TransformUtil.mapToObject(response, JobStatus.class);
		return jobStatus;
	}

	// 操作DBAAS服务实例
	private Response operateDBCSInstance(String operationType, LocalDbObject localobj) {
		// API 请求
		DbConnection dbCon = new DbConnection();
		JSONObject reqBody = new JSONObject();
		String targetUrl = localobj.getServiceUri();

		// 组装API地址
		if ("start".equals(operationType) || "stop".equals(operationType) || "restart".equals(operationType)) {
			reqBody.put("lifecycleState", operationType);
		}
		else if ("backup".equals(operationType)) {
			targetUrl += "/backups";
		}
		else if ("recovery".equals(operationType)) {
			targetUrl += "/backups/recovery";
			reqBody.put("latest", "true"); // 恢复最新的备份
		}
		else {
			return Response.status(Response.Status.BAD_REQUEST)
					.entity("Operation Not Supported!")
					.type(MediaType.TEXT_PLAIN).build();
		}

		/**
		 *  reqParameters: 请求Body
		 *  1. start/stop/restart
		 *  	{"lifecycleState", "start"}
		 *  2. backup
		 *  	{}
		 *  3. recovery (backup存在为前提)
		 *  	{"tag": "TAG20170616T071444"}
		 */

		// API 返回
		Response response = dbCon.reqPost(reqBody, targetUrl);
		String responseStr = response.readEntity(String.class);
		String contentType = response.getHeaderString("content-type");

		System.out.println(">>>>>> Operate DBCSInstance Response:\n" + response);
		System.out.println(">>>>>> Response Body:\n" + responseStr);

		// 根据返回值更新Local数据库
		if (response.getStatus() !=  202) {
			localobj.setJobId(null);
		}
		else {
			localobj.setJobId(response.getLocation().toString()); //操作成功202， header中返回job id
		}

		localobj.setServiceUri(localobj.getServiceUri());
		localobj.setRep_status(String.valueOf(response.getStatus()));
		localobj.setRep_CreateTime(getOracleTimestamp(response.getDate()));
		localobj.setRep_LastModifiedTime(getOracleTimestamp(response.getLastModified()));
		localobj.setReq_UpdateTime(localobj.getReq_UpdateTime());

		String sql = dbCon.getLocalSql().get("updateSQLByInsertReply");
		// 向本地数据库更新数据
		dbCon.updateSQLByInsertReply(sql, localobj);

		return Response.status(response.getStatus()).entity(responseStr).type(contentType).build();
	}

	/**
	 *  打补丁
	 */
	private Response applyPatches(LocalDbObject localobj) {
		// 查询所有可用补丁
		List<DbaasPatch> patchList = getAvailablePatches(localobj.getInstanceId());
		// 打补丁
		if(!patchList.isEmpty()) {
			DbConnection dbCon = new DbConnection();
			HashMap<String, String> daasInfo = dbCon.getOracleCloudAccInfo();
			String targetUrl = daasInfo.get("mgmtUrl") + localobj.getInstanceId() + "/patches/";

			// 强制操作 forceApply
			JSONObject body = new JSONObject();
			body.put("forceApply", true);

			// 遍历每一个patchId
			for (DbaasPatch p : patchList) {
				targetUrl += p.getPatchId();
				Response response = dbCon.reqPost(body, targetUrl);
				
				String responseStr = response.readEntity(String.class);
				String contentType = response.getHeaderString("content-type");

				System.out.println(">>>>>> Apply a Patch Response:\n" + response);
				System.out.println(">>>>>> Response Body:\n" + responseStr);

				// 根据返回值更新Local数据库
				if (response.getStatus() !=  202) {
					localobj.setJobId("");
				}
				else {
					localobj.setJobId(response.getLocation().toString()); //操作成功202， header中返回job id
				}

				localobj.setServiceUri(localobj.getServiceUri());
				localobj.setRep_status(String.valueOf(response.getStatus()));
				localobj.setRep_CreateTime(getOracleTimestamp(response.getDate()));
				localobj.setRep_LastModifiedTime(getOracleTimestamp(response.getLastModified()));
				localobj.setReq_UpdateTime(localobj.getReq_UpdateTime());

				String sql = dbCon.getLocalSql().get("updateSQLByInsertReply");
				// 向本地数据库更新数据
				dbCon.updateSQLByInsertReply(sql, localobj);

				if (response.getStatus() !=  202) {
					return Response.status(response.getStatus()).entity(responseStr).type(contentType).build();
				}
			}
			
			return Response.status(Response.Status.ACCEPTED).build();

		}
		else {
			return Response.ok().entity("No Patch Available").type(MediaType.TEXT_PLAIN).build();
		}
	}

	private List<DbaasPatch> getAvailablePatches(String instanceId) {
		DbConnection dbCon = new DbConnection();
		HashMap<String, String> daasInfo = dbCon.getOracleCloudAccInfo();

		String targetUrl = daasInfo.get("mgmtUrl") + instanceId + "/patches/available";
		String response = dbCon.reqGet(targetUrl);

		List<DbaasPatch> patchList = TransformUtil.mapToObject(response, new TypeReference<List<DbaasPatch>>(){});
		return patchList;
	}

	// 删除DBAAS服务实例
	private JSONObject deleteDBCSInstance(String ramdonId,LocalDbObject localobj){
		// API 请求
		DbConnection dbCon = new DbConnection();
		String targetUrl = localobj.getServiceUri();
		Response response = dbCon.reqDelete(targetUrl);

		// 根据返回值更新Local数据库
		localobj.setJobId(response.getLocation().toString());
		localobj.setServiceUri(localobj.getServiceUri());
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
	private Timestamp getOracleTimestamp(Date date) {
		try {
			return new Timestamp(date.getTime());
		} catch (Exception e) {
			return null;
		}
	}

	private JSONObject createJCSInstance(ServiceInstance body) {
		// TODO Auto-generated method stub
		return null;
	}

	private String getServiceNameById(String serviceId) {
		String serviceName = "";

		if (serviceId == null || serviceId.equals("")) {
			return serviceName;
		}

		List<Service> serviceList = TransformUtil.getCatalog().getSuppliers().get(0).getServices();
		for (Service s : serviceList) {
			if (s.getId().equals(serviceId)) {
				return s.getName();
			}
		}

		return serviceName;
	}
	
	private String getServiceIdByName(String serviceName) {
		String serviceId = "";

		if (serviceName == null || serviceName.equals("")) {
			return serviceId;
		}

		List<Service> serviceList = TransformUtil.getCatalog().getSuppliers().get(0).getServices();
		for (Service s : serviceList) {
			if (s.getName().equals(serviceName)) {
				return s.getId();
			}
		}

		return serviceName;
	}

}
