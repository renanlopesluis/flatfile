package com.ilegra.flatfile.service.converter;

import com.ilegra.flatfile.model.CustomerDataRow;
import com.ilegra.flatfile.model.DataRow;

public class CustomerDataRowConverter extends DataRowConverter{

	private static final int CNPJ_INDEX = 1;
	private static final int NAME_INDEX = 2;
	private static final int BUSINESS_AREA_INDEX = 3;

	@Override
	public DataRow convert(String row) {
		String[] data = row.split(SPLITTER);
		String cnpj = data[CNPJ_INDEX];
		String name = data[NAME_INDEX];
		String businessArea = data[BUSINESS_AREA_INDEX];
		return new CustomerDataRow(cnpj, name, businessArea);
	}

}
