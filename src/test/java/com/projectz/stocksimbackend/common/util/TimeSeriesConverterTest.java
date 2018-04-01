package com.projectz.stocksimbackend.common.util;

import com.projectz.stocksimbackend.common.proto.TimeSeriesProto;
import com.projectz.stocksimbackend.common.stockapi.AlphaVantageAPIService;
import com.projectz.stocksimbackend.common.stockapi.TimeSeriesIntradayResponse;
import com.projectz.stocksimbackend.common.stockapi.TimeSeriesResponse;
import com.projectz.stocksimbackend.common.stockapi.TimeSeriesResponseParser;


import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.projectz.stocksimbackend.common.util.TimeSeriesProtoHelper.printTimeSeriesProto;

public class TimeSeriesConverterTest {
  private static final String TEST_DIR =
      "src/test/java/com/projectz/stocksimbackend/common/util/";

  @Before
  public void setup() {

  }

  @Test
  public void testTimeSeriesConverterTest() throws Exception {
    byte[] encoded = Files.readAllBytes(Paths.get(TEST_DIR+"SampleTimeSeriesIntradayData.txt"));
    String rawString = new String(encoded, Charset.defaultCharset());

    TimeSeriesResponse timeSeriesResponse =
        TimeSeriesResponseParser.parsetTimeSeriesResponse(rawString);

  }


}
