package com.ilegra.flatfile.service.statistics;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.ilegra.flatfile.io.DATFileStream;
import com.ilegra.flatfile.io.FileConfiguration;
import com.ilegra.flatfile.io.FileStream;
import com.ilegra.flatfile.io.FileManager;
import com.ilegra.flatfile.model.DataRow;
import com.ilegra.flatfile.model.Summary;
import com.ilegra.flatfile.service.converter.Converter;
import com.ilegra.flatfile.service.converter.FlatFileConverter;

@Service
@Component("dataRowStatistic")
public class DataRowStatistic implements Statistic{
	
	@Autowired
	private Summarizer summarizer;
	
	@Override
	public void process() throws Exception {
		List<Path> paths = FileManager.getAllFilesFromDirectory();
		for (Path path : paths) {
			List<String> lines = readFile(path);
			List<DataRow> dataRows = rowsToDataRows(lines);
			writeStatistics(dataRows, path);
		}
	}
	
	private List<String> readFile(Path path) throws IOException {
		FileStream file = new DATFileStream(path.toString());
		List<String> lines = file.read();
		file.delete(path.toFile());
		return lines;
	}	
	
	private void writeStatistics(List<DataRow> rows, Path path) throws IOException {
		String fileName = FileManager.extractFileName(path);
		Summary summary = summarizer.summarize(rows);
		FileStream fileStream = new DATFileStream(String.format(FileConfiguration.OUT_PATH.toString(), fileName));
		fileStream.write(summary.toLines());
	}
	
	private List<DataRow> rowsToDataRows(List<String> lines) throws Exception{
		List<DataRow> dataRows = new ArrayList<>();
		Converter<DataRow> converter = new FlatFileConverter();
		for (String line : lines) {
			DataRow row = converter.convert(line);
			dataRows.add(row);
		}
		return dataRows;
	}
}
