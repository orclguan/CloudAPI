package com.oracle.citiccloud.model.dbaas.resp;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/**
 * View All Service Instances : Response body
 * 
 * <a href="http://docs.oracle.com/en/cloud/paas/database-dbaas-cloud/csdbr/op-paas-service-dbcs-api-v1.1-instances-%7BidentityDomainId%7D-get.html#response">
 * REST API for Oracle Database Cloud Service</a>.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DbaasViewList {

	private String implementation_version;
	private String service_type;
	private List<DbaasView> services;
	private String uri;

	public String getImplementation_version() {
		return implementation_version;
	}
	public void setImplementation_version(String implementation_version) {
		this.implementation_version = implementation_version;
	}
	public String getService_type() {
		return service_type;
	}
	public void setService_type(String service_type) {
		this.service_type = service_type;
	}
	public List<DbaasView> getServices() {
		return services;
	}
	public void setServices(List<DbaasView> services) {
		this.services = services;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	
}
