package com.projectz.stocksimbackend.common.proto;

import java.time.Instant;
import java.util.ArrayList;

public class TimeSeriesProto {
  private String symbol;
  private int interval; // unit seconds
  private Instant lastRefreshed;
  private Instant marketOpen;
  private Instant marketClose;
  private ArrayList<TimeSeriesValue> values;

  public String getSymbol() {
    return symbol;
  }

  public int getInterval() {
    return interval;
  }

  public Instant getLastRefreshed() {
    return lastRefreshed;
  }

  public Instant getMarketOpen() {
    return marketOpen;
  }

  public Instant getMarketClose() {
    return marketClose;
  }

  public ArrayList<TimeSeriesValue> getValues() {
    return values;
  }
}
