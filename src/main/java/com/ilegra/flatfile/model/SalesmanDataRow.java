package com.ilegra.flatfile.model;

import java.math.BigDecimal;

public class SalesmanDataRow extends DataRow{
	
	private String cpf;
	private String name;
	private BigDecimal salary;
	
	public SalesmanDataRow(String cpf, String name, BigDecimal salary) {
		super();
		this.cpf = cpf;
		this.name = name;
		this.salary = salary;
	}

	public String getCpf() {
		return cpf;
	}

	public String getName() {
		return name;
	}

	public BigDecimal getSalary() {
		return salary;
	}	

}
