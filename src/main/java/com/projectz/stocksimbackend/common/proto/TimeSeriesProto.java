package com.projectz.stocksimbackend.common.proto;

import java.lang.reflect.Array;
import java.time.Instant;
import java.util.ArrayList;

public class TimeSeriesProto {
  private String symbol;
  private String interval; // unit seconds
  private String lastRefreshed;
  private ArrayList<TimeSeriesValue> values;

  public String getSymbol() {
    return symbol;
  }

  public void setSymbol( String symbol ) { this.symbol = symbol; }

  public String getInterval() {
    return interval;
  }

  public void setInterval( String interval ) { this.interval = interval; }

  public String getLastRefreshed() {
    return lastRefreshed;
  }

  public void setLastRefreshed( String lastRefreshed ) { this.lastRefreshed = lastRefreshed; }

  public ArrayList<TimeSeriesValue> getValues() {
    return values;
  }

  public void setValues( ArrayList<TimeSeriesValue> values ) { this.values = values; }

}
