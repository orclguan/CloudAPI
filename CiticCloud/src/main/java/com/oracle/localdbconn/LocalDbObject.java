package com.oracle.localdbconn;

import java.sql.Timestamp;

public class LocalDbObject {
	
	private String instanceId;
	private String instanceType;
	private String oprationType;
	private Timestamp req_UpdateTime;
	private String jobId;
	private String serviceUri;
	private Timestamp rep_CreateTime;
	private Timestamp rep_LastModifiedTime;
	private String operationId;
	
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
	public void setRep_CreateTime(Timestamp rep_CreateTime) {
		this.rep_CreateTime = rep_CreateTime;
	}
	public String getOperationId() {
		return operationId;
	}
	public void setOperationId(String operationId) {
		this.operationId = operationId;
	}
	
}
