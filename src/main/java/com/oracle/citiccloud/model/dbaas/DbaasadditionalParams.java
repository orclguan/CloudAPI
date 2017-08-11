package com.oracle.citiccloud.model.dbaas;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DbaasadditionalParams {
	
	private String db_demo;

	public String getDb_demo() {
		return db_demo;
	}

	public void setDb_demo(String db_demo) {
		this.db_demo = db_demo;
	}
	
}
