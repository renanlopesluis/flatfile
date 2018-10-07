package com.ilegra.flatfile.converter;

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
import com.ilegra.flatfile.service.converter.Converter;
import com.ilegra.flatfile.service.converter.FlatFileConverter;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ConverterTest {

	private String errorMessage;

	@Test
	public void shouldConvertIntoSalesmanDataRow() {
		try {
			Converter<DataRow> converter = new FlatFileConverter();
			checkSalesmanDataRowAsserts((SalesmanDataRow) converter.convert("001ç3245678865434çRenatoç40000.99"));
		} catch (Exception e) {
			errorMessage = e.getMessage();
		}
	}

	@Test
	public void shouldConvertIntoCustormerDataRow() {
		try {
			Converter<DataRow>  converter = new FlatFileConverter();
			checkCustormerDataRowAsserts(
					(CustomerDataRow) converter.convert("002ç2345675434544345çJose da SilvaçRural"));
		} catch (Exception e) {
			errorMessage = e.getMessage();
		}
	}

	@Test
	public void shouldConvertIntoSaleDataRow() throws Exception {
		Converter<DataRow>  converter = new FlatFileConverter();
		checkSaleDataRowAsserts((SaleDataRow) converter.convert("003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çDiego"));
	}

	@Test
	public void shouldNotConvertIntoAnyDataRow() {
		DataRow dataRow = null;
		try {
			Converter<DataRow>  converter = new FlatFileConverter();
			dataRow = converter.convert("004ç10ç[1-10-100,2-30-2.50,3-40-3.10]çDiego");
		} catch (Exception ex) {
			errorMessage = ex.getMessage();
		} finally {
			checkDataRowAssert(dataRow);
		}
	}

	private void checkSalesmanDataRowAsserts(SalesmanDataRow salesmanDataRow) {
		Assert.assertNull(errorMessage);
		Assert.assertEquals("3245678865434", salesmanDataRow.getCpf());
		Assert.assertEquals("Renato", salesmanDataRow.getName());
		Assert.assertEquals(new BigDecimal("40000.99"), salesmanDataRow.getSalary());
	}

	private void checkCustormerDataRowAsserts(CustomerDataRow customerDataRow) {
		Assert.assertNull(errorMessage);
		Assert.assertEquals("2345675434544345", customerDataRow.getCnpj());
		Assert.assertEquals("Jose da Silva", customerDataRow.getName());
		Assert.assertEquals("Rural", customerDataRow.getBusinessArea());
	}

	private void checkSaleDataRowAsserts(SaleDataRow saleDataRow) {
		Assert.assertNull(errorMessage);
		Assert.assertEquals(10, saleDataRow.getId().intValue());
		Assert.assertEquals("Diego", saleDataRow.getSalesmanName());
		List<Item> items = buildItems();
		Assert.assertEquals(items.size(), saleDataRow.getItems().size());
		for (Item item : items) {
			Assert.assertTrue(saleDataRow.getItems().stream().anyMatch(x -> x.getId().equals(item.getId())
					&& x.getQuantity().equals(item.getQuantity()) && x.getPrice().equals(item.getPrice())));
		}
	}

	private void checkDataRowAssert(DataRow dataRow) {
		Assert.assertNull(dataRow);
		Assert.assertNotNull(errorMessage);
		Assert.assertEquals("No such enum for this id", errorMessage);
	}

	private List<Item> buildItems() {
		List<Item> items = new ArrayList<>();
		items.add(new Item(1, new BigDecimal("100"), 10));
		items.add(new Item(2, new BigDecimal("2.50"), 30));
		items.add(new Item(3, new BigDecimal("3.10"), 40));
		return items;
	}

}
