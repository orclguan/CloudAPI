package com.oracle.citiccloud.model.dbaas.resp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/**
 *  View a List of Available Patches - Response Body
 *  
 * <a href="http://docs.oracle.com/en/cloud/paas/database-dbaas-cloud/csdbr/op-paas-api-v1.1-instancemgmt-%7BidentityDomainId%7D-services-dbaas-instances-%7BserviceId%7D-patches-available-get.html#response">
 * REST API for Oracle Database Cloud Service</a>.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DbaasPatch {
	// 暂时只列出需要的字段
	private String patchId;
	private String entryDate; //Date and time the patch is created.

	public String getPatchId() {
		return patchId;
	}
	public void setPatchId(String patchId) {
		this.patchId = patchId;
	}
	public String getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}
}
