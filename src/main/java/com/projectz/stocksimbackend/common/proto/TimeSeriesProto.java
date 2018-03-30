package com.projectz.stocksimbackend.common.proto;

import java.lang.reflect.Array;
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

  public void setSymbol( String symbol ) { this.symbol = symbol; }

  public int getInterval() {
    return interval;
  }

  public void setInterval( int interval ) { this.interval = interval; }

  public Instant getLastRefreshed() {
    return lastRefreshed;
  }

  public void setLastRefreshed( Instant lastRefreshed ) { this.lastRefreshed = lastRefreshed; }

  public Instant getMarketOpen() { 
    return marketOpen; 
  }

  public void setMarketOpen( Instant marketOpen ) { this.marketOpen = marketOpen; }

  public Instant getMarketClose() {
    return marketClose;
  }

  public void setMarketClose( Instant marketClose ) { this.marketClose = marketClose; }

  public ArrayList<TimeSeriesValue> getValues() {
    return values;
  }

  public void setValues( ArrayList<TimeSeriesValue> values ) { this.values = values; }
}
