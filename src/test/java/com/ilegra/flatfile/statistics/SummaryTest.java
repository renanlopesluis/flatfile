package com.ilegra.flatfile.statistics;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ilegra.flatfile.model.CustomerDataRow;
import com.ilegra.flatfile.model.DataRow;
import com.ilegra.flatfile.model.Item;
import com.ilegra.flatfile.model.SaleDataRow;
import com.ilegra.flatfile.model.SalesmanDataRow;
import com.ilegra.flatfile.model.Summary;
import com.ilegra.flatfile.service.statistics.SalesSummarizer;
import com.ilegra.flatfile.service.statistics.Summarizer;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SummaryTest {
	
	private List<DataRow> dataRows;
	private List<Item> items = new ArrayList<>();
	
	@Test
	public void shouldSummarizeADataRowList() {
		dataRows = new ArrayList<>();
		items = new ArrayList<>();
		
		buildDataRow();
		
		Summarizer summarizer = new SalesSummarizer();
		Summary summary = summarizer.summarize(dataRows);
		assertsResult(summary);
	}

	private void buildDataRow() {
		dataRows.add(new SalesmanDataRow("123", "Caroline", new BigDecimal("3000.89")));
		dataRows.add(new SalesmanDataRow("353", "Rambo", new BigDecimal("1000.20")));
		dataRows.add(new SalesmanDataRow("201", "Joey", new BigDecimal("1502.65")));

		dataRows.add(new CustomerDataRow("321", "Ellen Page", "Comercio"));
		dataRows.add(new CustomerDataRow("576", "Norton", "Rural"));
		dataRows.add(new CustomerDataRow("798", "François", "Comercio"));
		dataRows.add(new CustomerDataRow("808", "Durden", "Rural"));

		items.add(new Item(1, new BigDecimal ("5"),3 ));
		dataRows.add(new SaleDataRow(10L, items, "Caroline"));

		items = new ArrayList<>();
		items.add(new Item(3, new BigDecimal("1"), 1));
		dataRows.add(new SaleDataRow(11L, items, "Rambo"));
		
		items = new ArrayList<>();
		items.add(new Item(1, new BigDecimal("3"), 4));
		items.add(new Item(2, new BigDecimal("2"), 8));
		dataRows.add(new SaleDataRow(12L, items, "Joey"));

	}
	
	private void assertsResult(Summary summary) {
		Assert.assertEquals(3, summary.getSalesmenAmount().intValue());
		Assert.assertEquals(4, summary.getClientsAmount().intValue());
		Assert.assertEquals(12, summary.getMostExpensiveSaleId().intValue());
		Assert.assertEquals("Rambo", summary.getWorstSalesman());
	}

}
