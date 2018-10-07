package com.ilegra.flatfile.service.converter;

public interface Converter<T> {
	T convert(String row) throws Exception;
	
}
