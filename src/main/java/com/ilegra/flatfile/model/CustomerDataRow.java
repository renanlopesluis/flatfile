package com.ilegra.flatfile.model;

public class CustomerDataRow extends DataRow{
	
	private String cnpj;
	private String name;
	private String businessArea;

	public CustomerDataRow(String cnpj, String name, String businessArea) {
		super();
		this.cnpj = cnpj;
		this.name = name;
		this.businessArea = businessArea;
	}

	public String getCnpj() {
		return cnpj;
	}

	public String getName() {
		return name;
	}

	public String getBusinessArea() {
		return businessArea;
	}

}
