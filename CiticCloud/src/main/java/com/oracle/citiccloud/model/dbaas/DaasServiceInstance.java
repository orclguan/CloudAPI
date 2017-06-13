package com.oracle.citiccloud.model.dbaas;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DaasServiceInstance {

	// 中信Request Body对象
	private String instance_id;

	private String service_id;

	private String org_id;

	private Boolean accepts_incomplete;

	private ParaOfOraBody parameters;

	private PricingModel pricing_model;

	public String getInstance_id() {
		return instance_id;
	}

	public void setInstance_id(String instance_id) {
		this.instance_id = instance_id;
	}

	public String getService_id() {
		return service_id;
	}

	public void setService_id(String service_id) {
		this.service_id = service_id;
	}

	public String getOrg_id() {
		return org_id;
	}

	public void setOrg_id(String org_id) {
		this.org_id = org_id;
	}

	public Boolean getAccepts_incomplete() {
		return accepts_incomplete;
	}

	public void setAccepts_incomplete(Boolean accepts_incomplete) {
		this.accepts_incomplete = accepts_incomplete;
	}

	public PricingModel getPricing_model() {
		return pricing_model;
	}

	public void setPricing_model(PricingModel pricing_model) {
		this.pricing_model = pricing_model;
	}

	public ParaOfOraBody getParameters() {
		return parameters;
	}

	public void setParameters(ParaOfOraBody parameters) {
		this.parameters = parameters;
	}

}
