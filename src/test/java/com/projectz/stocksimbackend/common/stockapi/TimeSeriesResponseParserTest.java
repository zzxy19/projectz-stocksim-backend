package com.projectz.stocksimbackend.common.stockapi;

import com.projectz.stocksimbackend.common.sample.SampleDataReader;
import org.junit.Before;
import org.junit.Test;

public class TimeSeriesResponseParserTest {
  @Before
  public void setup() {

  }

  @Test
  public void testTimeSeriesParsing() throws Exception {
    TimeSeriesResponse expectedResponse =
      SampleDataReader.getExpectedTimeSeriesResponseFromIntradayData();
    TimeSeriesResponse response = SampleDataReader.readTimeSeriesResponseFromIntradayData();
    assert(TimeSeriesResponseHelper.equals(response, expectedResponse));
  }


}
