package com.projectz.stocksimbackend.common.util;

import com.projectz.stocksimbackend.common.proto.timeseries.TimeSeriesProto;
import com.projectz.stocksimbackend.common.proto.timeseries.TimeSeriesValue;
import com.projectz.stocksimbackend.common.stockapi.TimeSeriesResponse;
import com.projectz.stocksimbackend.common.stockapi.TimeSeriesResponseParser;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class TimeSeriesConverterTest {
  private static final String TEST_DIR =
      "src/test/java/com/projectz/stocksimbackend/common/sample/";

  @Before
  public void setup() {}

  @Test
  public void testTimeSeriesConverterTest() throws Exception {
    File file = new File(TEST_DIR + "SampleTimeSeriesIntradayData.txt");
    InputStreamReader inputReader = new FileReader(file);

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

    TimeSeriesResponse timeSeriesResponse =
        TimeSeriesResponseParser.parseTimeSeriesResponse(inputReader);
    TimeSeriesProto timeSeriesProto =
        TimeSeriesConverter.convertTimeSeriesResponseToProto(timeSeriesResponse);


    assert (TimeSeriesProtoHelper.equals(timeSeriesProto, expectedTSP));
  }


}
