	package com.ilegra.flatfile.statistics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ilegra.flatfile.io.DATFileStream;
import com.ilegra.flatfile.io.FileConfiguration;
import com.ilegra.flatfile.io.FileStream;
import com.ilegra.flatfile.service.statistics.Statistic;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class StatisticTest {
	
	@Autowired
	@Qualifier("dataRowStatistic")
	Statistic statistic;

	@Test
	public void shouldProcessStatistics() throws Exception {
		List<String> lines = new ArrayList<>();

		lines.add("001ç1234567891234çDiegoç50000");
		lines.add("001ç3245678865434çRenatoç40000.99");
		lines.add("002ç2345675434544345çJose da SilvaçRural");
		lines.add("002ç2345675433444345çEduardo PereiraçRural");
		lines.add("003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çDiego");
		lines.add("003ç08ç[1-34-10,2-33-1.50,3-40-0.10]çRenato");

		List<String> result = processFile("data", lines);
		assertsResult(result);
	}
			
	private List<String> processFile(String path, List<String> lines) throws IOException, Exception {
		String inPath = FileConfiguration.IN_PATH+"\\"+path+FileConfiguration.EXTENSION;
		buildDataFile(lines, inPath);

		statistic.process();

		String outPath = String.format(FileConfiguration.OUT_PATH.toString(), path);
		FileStream fileStream = new DATFileStream(outPath);
		 return fileStream.read();	
	}
	
	private void buildDataFile(List<String> lines, String inPath) throws IOException {
		FileStream fileStream = new DATFileStream(inPath);
		fileStream.write(lines);
	}
	
	private void assertsResult(List<String> resultLines) {
		Assert.assertEquals("Amount of clients: 2", resultLines.get(0));
		Assert.assertEquals("Amount of salesmen: 2", resultLines.get(1));
		Assert.assertEquals("ID of the most expensive sale: 10", resultLines.get(2));
		Assert.assertEquals("Worst salesman ever: Renato", resultLines.get(3));
	}
}
