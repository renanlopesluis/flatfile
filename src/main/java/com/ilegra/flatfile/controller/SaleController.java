package com.ilegra.flatfile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import com.ilegra.flatfile.service.statistics.Statistic;

@Controller
public class SaleController {

	@Autowired
	@Qualifier("dataRowStatistic")
	private Statistic statistic;

	public void processStatistics() {
		try {
			statistic.process();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
