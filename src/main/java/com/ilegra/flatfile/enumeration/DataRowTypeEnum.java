package com.ilegra.flatfile.enumeration;

import java.util.Arrays;
import java.util.List;

import com.ilegra.flatfile.exception.NoSuchEnumException;
import com.ilegra.flatfile.service.converter.CustomerDataRowConverter;
import com.ilegra.flatfile.service.converter.DataRowConverter;
import com.ilegra.flatfile.service.converter.SaleDataRowConverter;
import com.ilegra.flatfile.service.converter.SalesmanDataRowConverter;


public enum DataRowTypeEnum {

	SALESMAN	("001", new SalesmanDataRowConverter()),
	CUSTOMER	("002", new CustomerDataRowConverter()),
	SALE		("003", new SaleDataRowConverter());
	
	private String id;
	private DataRowConverter converter;
	
	private DataRowTypeEnum(String id, DataRowConverter converter) {
		this.id = id;
		this.converter = converter;
	}
	
	public DataRowConverter getDataRowConverter() {
		return converter;
	}
	
	public static DataRowTypeEnum getFromId(String id) throws NoSuchEnumException {
		List<DataRowTypeEnum> types = Arrays.asList(values());
		return types.stream().filter(x -> x.id.equals(id)).findFirst()
				.orElseThrow(() -> new NoSuchEnumException("No such enum for this id"));
	}
}
