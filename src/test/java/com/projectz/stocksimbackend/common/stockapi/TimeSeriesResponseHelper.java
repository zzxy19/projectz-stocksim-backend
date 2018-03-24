package com.projectz.stocksimbackend.common.stockapi;

import java.util.Map;

public class TimeSeriesResponseHelper {
  public static boolean equals(
      TimeSeriesIntradayResponse one, TimeSeriesIntradayResponse other) {
    if (!equals(one.getMetadata(), other.getMetadata())) {
      return false;
    }
    for (String time : one.getTimeSeries().keySet()) {
      if (!other.getTimeSeries().containsKey(time)) {
        return false;
      }
      if (!equals(one.getTimeSeries().get(time), other.getTimeSeries().get(time))) {
        return false;
      }
    }
    return true;
  }

  private static <T1, T2> boolean equals(Map<T1, T2> a, Map<T1, T2> b) {
    if (a.size() != b.size()) {
      return false;
    }
    for (T1 key : a.keySet()) {
      if (!b.containsKey(key)) {
        return false;
      }
      if (!a.get(key).equals(b.get(key))) {
        return false;
      }
    }
    return true;
  }
}
