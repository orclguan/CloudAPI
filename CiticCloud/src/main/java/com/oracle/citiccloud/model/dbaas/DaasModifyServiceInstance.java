package com.oracle.citiccloud.model.dbaas;

public class DaasModifyServiceInstance {
	
	// 中信Request Body对象
		private String operation;

		private Boolean accepts_incomplete;

		private ParaOfOraBody parameters;

		private PricingModel pricing_model;

		public String getOperation() {
			return operation;
		}

		public void setOperation(String operation) {
			this.operation = operation;
		}

		public Boolean getAccepts_incomplete() {
			return accepts_incomplete;
		}

		public void setAccepts_incomplete(Boolean accepts_incomplete) {
			this.accepts_incomplete = accepts_incomplete;
		}

		public ParaOfOraBody getParameters() {
			return parameters;
		}

		public void setParameters(ParaOfOraBody parameters) {
			this.parameters = parameters;
		}

		public PricingModel getPricing_model() {
			return pricing_model;
		}

		public void setPricing_model(PricingModel pricing_model) {
			this.pricing_model = pricing_model;
		}
		
		
}
