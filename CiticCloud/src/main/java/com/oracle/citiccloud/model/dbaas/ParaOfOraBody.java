package com.oracle.citiccloud.model.dbaas;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ParaOfOraBody {

	// Oracle OPC 的 Body 参数
	private String description;
	private String edition; // 必填
	private String level; // 必填
	private ArrayList<DbaasParameter> parameters; // 必填
	private String serviceName; // 必填
	private String shape; // 必填
	private String subscriptionType; // 必填
	private String version; // 必填
	private String vmPublicKeyText; // 必填

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEdition() {
		return edition;
	}

	public void setEdition(String edition) {
		this.edition = edition;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public ArrayList<DbaasParameter> getParameters() {
		return parameters;
	}

	public void setParameters(ArrayList<DbaasParameter> parameters) {
		this.parameters = parameters;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getShape() {
		return shape;
	}

	public void setShape(String shape) {
		this.shape = shape;
	}

	public String getSubscriptionType() {
		return subscriptionType;
	}

	public void setSubscriptionType(String subscriptionType) {
		this.subscriptionType = subscriptionType;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getVmPublicKeyText() {
		return vmPublicKeyText;
	}

	public void setVmPublicKeyText(String vmPublicKeyText) {
		this.vmPublicKeyText = vmPublicKeyText;
	}

}
