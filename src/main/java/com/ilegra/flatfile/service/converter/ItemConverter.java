package com.ilegra.flatfile.service.converter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.ilegra.flatfile.model.Item;

public class ItemConverter implements Converter<Item> {
	
	private static final String ITEMS_SPLITTER = ",";
	private static final String ITEM_SPLITTER = "-";
	
	private static final int ID_ITEM_INDEX = 0;
	private static final int QUANTITY_ITEM_INDEX = 1;
	private static final int PRICE_ITEM_INDEX = 2;
	
	public List<Item> convertItems(String itemsRow) {
		List<Item> items = new ArrayList<>();
		String[] itemArray = itemsRow.substring(1, itemsRow.length() - 1).split(ITEMS_SPLITTER);
		for (String itemString : itemArray) {
			Item item = convert(itemString);
			items.add(item);
		}
		return items;
	}
	
	@Override
	public Item convert(String row) {
		String[] itemData = row.split(ITEM_SPLITTER);
		Integer id = new Integer(itemData[ID_ITEM_INDEX]);
		Integer quantity = new Integer(itemData[QUANTITY_ITEM_INDEX]);
		BigDecimal price = new BigDecimal(itemData[PRICE_ITEM_INDEX]);
		return new Item(id, price, quantity);
	}
}
