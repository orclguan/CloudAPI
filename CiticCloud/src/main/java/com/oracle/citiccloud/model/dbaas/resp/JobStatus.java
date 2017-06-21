package com.oracle.citiccloud.model.dbaas.resp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * View the Job Status of an Operation - Response Body
 * <a href="http://docs.oracle.com/en/cloud/paas/database-dbaas-cloud/csdbr/op-paas-service-dbcs-api-v1.1-instances-%7BidentityDomainId%7D-status-%7BrequestName%7D-job-%7BjobId%7D-get.html#response">
 * REST API for Oracle Database Cloud Service</a>.
 * 
 * @author xuelli
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class JobStatus {
	//暂时只定义了部分字段
	private String job_operation;
	private String job_start_date; //GMT
	private String job_end_date; //GMT
	private String job_status;
	private String[] message;
	private String service_name;

	public String getJob_operation() {
		return job_operation;
	}
	public void setJob_operation(String job_operation) {
		this.job_operation = job_operation;
	}
	public String getJob_start_date() {
		return job_start_date;
	}
	public void setJob_start_date(String job_start_date) {
		this.job_start_date = job_start_date;
	}
	public String getJob_end_date() {
		return job_end_date;
	}
	public void setJob_end_date(String job_end_date) {
		this.job_end_date = job_end_date;
	}
	public String getJob_status() {
		return job_status;
	}
	public void setJob_status(String job_status) {
		this.job_status = job_status;
	}
	public String[] getMessage() {
		return message;
	}
	public void setMessage(String[] message) {
		this.message = message;
	}
	public String getService_name() {
		return service_name;
	}
	public void setService_name(String service_name) {
		this.service_name = service_name;
	}
}
