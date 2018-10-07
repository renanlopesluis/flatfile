package com.ilegra.flatfile.model;

import java.math.BigDecimal;

public class Item {
	private Integer id;
	private BigDecimal price;
	private Integer quantity;

	public Item(Integer id, BigDecimal price, Integer quantity) {
		super();
		this.id = id;
		this.price = price;
		this.quantity = quantity;
	}

	public Integer getId() {
		return id;
	}
	
	public BigDecimal getPrice() {
		return price;
	}
	
	public Integer getQuantity() {
		return quantity;
	}

	public BigDecimal getTotalPrice() {
		return price.multiply(new BigDecimal(quantity));
	}
}