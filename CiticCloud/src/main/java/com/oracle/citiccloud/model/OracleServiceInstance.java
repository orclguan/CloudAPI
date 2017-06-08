/**
 * 
 */
package com.oracle.citiccloud.model;

import com.oracle.citiccloud.model.dbaas.OraDbaasParameters;
import com.oracle.citiccloud.model.dbaas.PricingModel;

/**
 * @author Ray.Nan
 *
 */
public class OracleServiceInstance {

	private String instanceId;
	private String serviceId;
	private String orgId;
	private Boolean acceptsIncomplete;
	private OraDbaasParameters oraParameters;
	private PricingModel pricingModel;
	public String getInstanceId() {
		return instanceId;
	}
	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}
	public String getServiceId() {
		return serviceId;
	}
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public Boolean getAcceptsIncomplete() {
		return acceptsIncomplete;
	}
	public void setAcceptsIncomplete(Boolean acceptsIncomplete) {
		this.acceptsIncomplete = acceptsIncomplete;
	}
/*	public OraParameters getOraParameters() {
		return oraParameters;
	}
	public void setOraParameters(OraParameters oraParameters) {
		this.oraParameters = oraParameters;
	}*/
	public PricingModel getPricingModel() {
		return pricingModel;
	}
	public void setPricingModel(PricingModel pricingModel) {
		this.pricingModel = pricingModel;
	}
	
	
}
