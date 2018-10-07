package com.ilegra.flatfile.model;

import java.math.BigDecimal;
import java.util.List;

public class SaleDataRow extends DataRow {
	
	private Long id;
	private List<Item> items;
	private String salesmanName;
	
	public SaleDataRow(Long id, List<Item> items, String salesmanName) {
		super();
		this.id = id;
		this.items = items;
		this.salesmanName = salesmanName;
	}
	
	public Long getId() {
		return id;
	}
	
	public List<Item> getItems() {
		return items;
	}
	
	public String getSalesmanName() {
		return salesmanName;
	}
	
	public Boolean hasSalesmanNamed(String salesmanName) {
		return this.salesmanName.equals(salesmanName);
	}
	
	public BigDecimal getSaleTotalAmount() {
		return items.stream().map((x) -> x.getTotalPrice()).reduce((x, y) -> x.add(y)).get();
	}
	
}
