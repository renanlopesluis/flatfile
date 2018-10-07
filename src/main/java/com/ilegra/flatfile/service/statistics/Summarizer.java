package com.ilegra.flatfile.service.statistics;

import java.util.List;

import com.ilegra.flatfile.model.DataRow;
import com.ilegra.flatfile.model.Summary;

public interface Summarizer {

	public Summary summarize(List<DataRow> rows);

}
