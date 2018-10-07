package com.ilegra.flatfile.service.converter;

import java.util.List;

import com.ilegra.flatfile.model.DataRow;
import com.ilegra.flatfile.model.Item;
import com.ilegra.flatfile.model.SaleDataRow;

public class SaleDataRowConverter extends DataRowConverter{
		
	private static final int ID_SALE_INDEX = 1;
	private static final int ITEMS_INDEX = 2;
	private static final int NAME_SALESMAN_INDEX = 3;
		
	@Override
	public DataRow convert(String row) {
		ItemConverter itemConverter = new ItemConverter();
		String[] data = row.split(SPLITTER);
		Long id = new Long(data[ID_SALE_INDEX]);
		String nameSalesman = data[NAME_SALESMAN_INDEX];
		List<Item> items = itemConverter.convertItems(data[ITEMS_INDEX]);
		return new SaleDataRow(id, items, nameSalesman);
	}
}
