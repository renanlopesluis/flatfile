package com.ilegra.flatfile.general;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.ilegra.flatfile.converter.ConverterTest;
import com.ilegra.flatfile.statistics.StatisticTest;
import com.ilegra.flatfile.statistics.SummaryTest;



@RunWith(Suite.class)
@SuiteClasses({ ConverterTest.class, SummaryTest.class, StatisticTest.class })
public class TestSuite {

}
