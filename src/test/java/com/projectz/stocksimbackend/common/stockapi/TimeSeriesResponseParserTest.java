package com.projectz.stocksimbackend.common.stockapi;

import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class TimeSeriesResponseParserTest {
  private static final String TEST_DIR =
    "src/test/java/com/projectz/stocksimbackend/common/sample/";

  @Before
  public void setup() {

  }

  @Test
  public void testTimeSeriesParsing() throws Exception {
    File file = new File(TEST_DIR + "SampleTimeSeriesIntradayData.txt");
    InputStreamReader inputReader = new FileReader(file);

    TimeSeriesResponse expectedResponse = new TimeSeriesResponse();
    Map<String, String> expectedMetadata = new LinkedHashMap<>();
    expectedMetadata.put("1. Information", "Intraday (15min) prices and volumes");
    expectedMetadata.put("2. Symbol", "MSFT");
    expectedMetadata.put("3. Last Refreshed", "2018-03-23 16:00:00");
    expectedMetadata.put("4. Interval", "15min");
    expectedMetadata.put("5. Output Size", "Compact");
    expectedMetadata.put("6. Time Zone", "US/Eastern");
    expectedResponse.setMetadata(expectedMetadata);

    Map<String, Map<String, String>> timeSeries = new LinkedHashMap<>();
    timeSeries.put(
      "2018-03-23 16:00:00",
      createTestTimeSeries("88.1000", "88.1400", "87.0800", "87.1800", "10674681"));
    timeSeries.put(
      "2018-03-23 15:45:00",
      createTestTimeSeries("88.4300", "88.4700", "88.0000", "88.0900", "2363268"));
    expectedResponse.setTimeSeries(timeSeries);

    TimeSeriesResponse response =
      TimeSeriesResponseParser.parseTimeSeriesResponse(inputReader);
    assert(TimeSeriesResponseHelper.equals(response, expectedResponse));
  }

  private Map<String, String> createTestTimeSeries(
      String open, String high, String low, String close, String volume) {
    Map<String, String> timeSeries = new LinkedHashMap<>();
    timeSeries.put("1. open", open);
    timeSeries.put("2. high", high);
    timeSeries.put("3. low", low);
    timeSeries.put("4. close", close);
    timeSeries.put("5. volume", volume);
    return timeSeries;
  }
}
