package com.ilegra.flatfile.service.statistics;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ilegra.flatfile.model.CustomerDataRow;
import com.ilegra.flatfile.model.DataRow;
import com.ilegra.flatfile.model.SaleDataRow;
import com.ilegra.flatfile.model.SalesmanDataRow;
import com.ilegra.flatfile.model.Summary;

@Service
public class SalesSummarizer implements Summarizer{

	@Override
	public Summary summarize(List<DataRow> rows) {
		return new Summary(getCustomersAmount(rows), 
				getSalesmenAmount(rows), 
				getTheMostExpensiveSaleId(rows), 
				getTheWorstSalesman(rows));
	}
	
	private List<SalesmanDataRow> getSalesmenList(List<DataRow> rows) {
		return rows.stream().filter(x -> x.isClass(SalesmanDataRow.class))
				.map(x -> (SalesmanDataRow) x)
				.collect(Collectors.toList());
	}

	private List<SaleDataRow> getSalesList(List<DataRow> rows) {
		return rows.stream().filter(x -> x.isClass(SaleDataRow.class))
				.map(x -> (SaleDataRow) x)
				.collect(Collectors.toList());
	}

	private Integer getSalesmenAmount(List<DataRow> rows) {
		return rows.stream().filter(x -> x.isClass(SalesmanDataRow.class))
				.collect(Collectors.toList()).size();
	}

	private Integer getCustomersAmount(List<DataRow> rows) {
		return rows.stream().filter(x -> x.isClass(CustomerDataRow.class))
				.collect(Collectors.toList()).size();
	}
	
	private BigDecimal getSalesTotalAmount(List<SaleDataRow> rows) {
		return rows.stream().map((x) -> x.getSaleTotalAmount()).reduce((x, y) -> x.add(y)).get();
	}

	private Long getTheMostExpensiveSaleId(List<DataRow> rows) {
		List<SaleDataRow> sales = getSalesList(rows);
		return Collections.max(sales, Comparator.comparing(SaleDataRow::getSaleTotalAmount)).getId();
	}

	private String getTheWorstSalesman(List<DataRow> rows) {
		List<SaleDataRow> sales = getSalesList(rows);
		List<SalesmanDataRow> salesmen = getSalesmenList(rows);
		
		Map<String, BigDecimal> salesMap = buildSalesMap(sales, salesmen);

		return getTheLeastSalesAmount(salesMap);
	}

	private Map<String, BigDecimal> buildSalesMap(List<SaleDataRow> sales,
			List<SalesmanDataRow> salesmen) {
		Map<String, BigDecimal> salesMap = new HashMap<>();
		for (SalesmanDataRow salesman : salesmen) {
			List<SaleDataRow> salesmenList = sales.stream().
					filter(x -> x.hasSalesmanNamed(salesman.getName()))
					.collect(Collectors.toList());
			
			BigDecimal sumSale = getSalesTotalAmount(salesmenList);
			salesMap.put(salesman.getName(), sumSale);
		}
		return salesMap;
	}
	
	private String getTheLeastSalesAmount(Map<String, BigDecimal> salesMap) {
		Entry<String, BigDecimal> leastAmount = 
				Collections.min(salesMap.entrySet(), Comparator.comparing(Entry::getValue));
		return leastAmount.getKey();
	}
}
