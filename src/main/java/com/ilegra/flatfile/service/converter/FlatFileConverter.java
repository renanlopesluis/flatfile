package com.ilegra.flatfile.service.converter;

import com.ilegra.flatfile.enumeration.DataRowTypeEnum;
import com.ilegra.flatfile.model.DataRow;

public class FlatFileConverter implements Converter<DataRow>{
	
	private static final int ID_BEGIN = 0;
	private static final int ID_END = 3;

	@Override
	public DataRow convert(String row) throws Exception{
		String id = row.substring(ID_BEGIN, ID_END);
		DataRowTypeEnum typeDataEnum = DataRowTypeEnum.getFromId(id);
		return typeDataEnum.getDataRowConverter().convert(row);
	}

}
