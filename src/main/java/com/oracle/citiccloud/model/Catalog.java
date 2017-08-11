package com.oracle.citiccloud.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 服务目录
 * @author xuelli
 *
 */
public class Catalog {
	private List<Supplier> suppliers = new ArrayList<Supplier>();

	public List<Supplier> getSuppliers() {
		return suppliers;
	}

	public void setSuppliers(List<Supplier> suppliers) {
		this.suppliers = suppliers;
	}
}
