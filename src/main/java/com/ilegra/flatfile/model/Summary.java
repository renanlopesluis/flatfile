package com.ilegra.flatfile.model;

import java.util.ArrayList;
import java.util.List;

public class Summary {
	
	private Integer clientsAmount;
	private Integer salesmenAmount;
	private Long mostExpensiveSaleId;
	private String worstSaleman;

	public Summary(Integer clientAmount, Integer salesmenAmount, Long mostExpensiveSaleId,
			String worstSalesman) {
		super();
		this.clientsAmount = clientAmount;
		this.salesmenAmount = salesmenAmount;
		this.mostExpensiveSaleId = mostExpensiveSaleId;
		this.worstSaleman = worstSalesman;
	}

	public Integer getClientsAmount() {
		return clientsAmount;
	}

	public Integer getSalesmenAmount() {
		return salesmenAmount;
	}

	public Long getMostExpensiveSaleId() {
		return mostExpensiveSaleId;
	}

	public String getWorstSalesman() {
		return worstSaleman;
	}

	public List<String> toLines() {
		List<String> lines = new ArrayList<>();
		lines.add("Amount of clients: " + this.clientsAmount);
		lines.add("Amount of salesmen: " + this.salesmenAmount);
		lines.add("ID of the most expensive sale: " + this.mostExpensiveSaleId);
		lines.add("Worst salesman ever: " + this.worstSaleman);
		return lines;
	}

}
