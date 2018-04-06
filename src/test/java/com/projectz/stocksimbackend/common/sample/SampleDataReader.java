package com.projectz.stocksimbackend.common.sample;

import com.projectz.stocksimbackend.common.proto.timeseries.TimeSeriesProto;
import com.projectz.stocksimbackend.common.proto.timeseries.TimeSeriesValue;
import com.projectz.stocksimbackend.common.stockapi.TimeSeriesResponse;
import com.projectz.stocksimbackend.common.stockapi.TimeSeriesResponseParser;
import com.projectz.stocksimbackend.common.util.TimeSeriesConverter;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class SampleDataReader {
  private static final String TEST_DIR =
    "src/test/java/com/projectz/stocksimbackend/common/sample/";

  public static TimeSeriesResponse readTimeSeriesResponseFromIntradayData() {
    try {
      File file = new File(TEST_DIR + "SampleTimeSeriesIntradayData.txt");
      InputStreamReader inputReader = new FileReader(file);
      TimeSeriesResponse response =
        TimeSeriesResponseParser.parseTimeSeriesResponse(inputReader);
      inputReader.close();
      return response;
    } catch (IOException ex) {
      throw new IllegalStateException(ex);
    }
  }

  public static TimeSeriesProto readTimeSeriesProtoFromIntradayData() {
    return TimeSeriesConverter.convertTimeSeriesResponseToProto(
      readTimeSeriesResponseFromIntradayData());
  }

  public static TimeSeriesResponse getExpectedTimeSeriesResponseFromIntradayData() {
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
    return expectedResponse;
  }

  public static TimeSeriesProto getExpectedTimeSeriesProtoFromIntradayData() {
    TimeSeriesProto expectedTSP = new TimeSeriesProto();

    expectedTSP.setSymbol("MSFT");
    expectedTSP.setLastRefreshed("2018-03-23 16:00:00");

    ArrayList<TimeSeriesValue> TSVs = new ArrayList<>();

    TimeSeriesValue tsv2 = new TimeSeriesValue();
    tsv2.setTime("2018-03-23 16:00:00");
    tsv2.setVolume(10674681);
    tsv2.setOpen((float) 88.1000);
    tsv2.setClose((float) 87.1800);
    tsv2.setHigh((float) 88.1400);
    tsv2.setLow((float) 87.0800);
    TSVs.add(tsv2);

    TimeSeriesValue tsv1 = new TimeSeriesValue();
    tsv1.setTime("2018-03-23 15:45:00");
    tsv1.setVolume(2363268);
    tsv1.setOpen((float) 88.4300);
    tsv1.setClose((float) 88.0900);
    tsv1.setHigh((float) 88.4700);
    tsv1.setLow((float) 88.0000);
    TSVs.add(tsv1);

    expectedTSP.setValues(TSVs);
    return expectedTSP;
  }

  private static Map<String, String> createTestTimeSeries(
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
