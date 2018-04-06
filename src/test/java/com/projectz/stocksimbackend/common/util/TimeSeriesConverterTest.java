package com.projectz.stocksimbackend.common.util;

import com.projectz.stocksimbackend.common.proto.timeseries.TimeSeriesProto;
import com.projectz.stocksimbackend.common.sample.SampleDataReader;
import com.projectz.stocksimbackend.common.stockapi.TimeSeriesResponse;

import org.junit.Before;
import org.junit.Test;

public class TimeSeriesConverterTest {
  @Before
  public void setup() {}

  @Test
  public void testTimeSeriesConverterTest() throws Exception {
    TimeSeriesProto expectedTSP =
      SampleDataReader.getExpectedTimeSeriesProtoFromIntradayData();
    TimeSeriesResponse timeSeriesResponse =
      SampleDataReader.readTimeSeriesResponseFromIntradayData();
    TimeSeriesProto timeSeriesProto =
        TimeSeriesConverter.convertTimeSeriesResponseToProto(timeSeriesResponse);
    assert(TimeSeriesProtoHelper.equals(timeSeriesProto, expectedTSP));
  }


}
