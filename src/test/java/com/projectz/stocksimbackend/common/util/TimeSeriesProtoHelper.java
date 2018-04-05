package com.projectz.stocksimbackend.common.util;

import com.projectz.stocksimbackend.common.proto.timeseries.TimeSeriesProto;
import com.projectz.stocksimbackend.common.proto.timeseries.TimeSeriesValue;

import java.util.ArrayList;

public class TimeSeriesProtoHelper {
  public static void printTimeSeriesProto(
      TimeSeriesProto tsp) {
    System.out.printf("Symbol: %s\n", tsp.getSymbol());
    printTimesSeriesValues(tsp.getValues());
  }

  public static void printTimesSeriesValues(
      ArrayList<TimeSeriesValue> tsvs) {
    for(TimeSeriesValue tsv : tsvs) {
      System.out.printf("Time: %s\n", tsv.getTime());
      System.out.printf("Volume: %s\n", tsv.getVolume());
    }
  }

}
