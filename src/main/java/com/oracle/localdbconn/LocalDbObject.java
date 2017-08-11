package com.oracle.localdbconn;

import java.sql.Timestamp;

public class LocalDbObject {
	
	//C请求的InstanceId
	private String req_instanceId;
	
	//O服务名_serviceName_serviceId
	private String instanceId;
	
	//C请求的服务类型dbaas或jaas id
	private String req_serviceId;
	
	//O提供的暂时dbaas或jaas
	private String instanceType;
	
	//C请求的公司ID,识别Oracle
	private String req_orgId;
	
	//每次操作对应的随机数，以区分不同的每次操作
	private String operationId;
	
	//C请求lifecycleState_操作类型_O操作类型_stop_start_restart
	private String oprationType;
	
	//接收C请求的时间
	private Timestamp req_UpdateTime;
	
	//O_jobURL_Location
	private String jobId;
	
	//O_ServiceUrl
	private String serviceUri;
	
	//O_返回状态
	private String rep_status;
	
	//O返回创建时间creation_time
	private Timestamp rep_CreateTime;
	
	//O返回最后更新时间last_modified_time
	private Timestamp rep_LastModifiedTime;
	
	public String getInstanceId() {
		return instanceId;
	}
	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}
	public String getInstanceType() {
		return instanceType;
	}
	public void setInstanceType(String instanceType) {
		this.instanceType = instanceType;
	}
	public String getOprationType() {
		return oprationType;
	}
	public void setOprationType(String oprationType) {
		this.oprationType = oprationType;
	}
	public String getJobId() {
		return jobId;
	}
	public void setJobId(String jobId) {
		this.jobId = jobId;
	}
	public String getServiceUri() {
		return serviceUri;
	}
	public void setServiceUri(String serviceUri) {
		this.serviceUri = serviceUri;
	}
	public Timestamp getReq_UpdateTime() {
		return req_UpdateTime;
	}
	public void setReq_UpdateTime(Timestamp req_UpdateTime) {
		this.req_UpdateTime = req_UpdateTime;
	}
	public Timestamp getRep_LastModifiedTime() {
		return rep_LastModifiedTime;
	}
	public void setRep_LastModifiedTime(Timestamp rep_LastModifiedTime) {
		this.rep_LastModifiedTime = rep_LastModifiedTime;
	}
	public Timestamp getRep_CreateTime() {
		return rep_CreateTime;
	}
	public void setRep_CreateTime(Timestamp timestamp) {
		this.rep_CreateTime = timestamp;
	}
	public String getOperationId() {
		return operationId;
	}
	public void setOperationId(String operationId) {
		this.operationId = operationId;
	}
	public String getReq_instanceId() {
		return req_instanceId;
	}
	public void setReq_instanceId(String req_instanceId) {
		this.req_instanceId = req_instanceId;
	}
	public String getReq_serviceId() {
		return req_serviceId;
	}
	public void setReq_serviceId(String req_serviceId) {
		this.req_serviceId = req_serviceId;
	}
	public String getReq_orgId() {
		return req_orgId;
	}
	public void setReq_orgId(String req_orgId) {
		this.req_orgId = req_orgId;
	}
	public String getRep_status() {
		return rep_status;
	}
	public void setRep_status(String rep_status) {
		this.rep_status = rep_status;
	}
	
}
