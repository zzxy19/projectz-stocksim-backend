package com.projectz.stocksimbackend.common.proto.timeseries;

import com.projectz.stocksimbackend.common.proto.common.DateRange;

import java.util.List;

public class TimeSeriesProto {
  private String symbol;
  private int interval; // unit seconds
  private DateRange dateRange;
  private String lastRefreshed;
  private List<TimeSeriesValue> values;

  public int getInterval() {
    return interval;
  }

  public void setInterval(int interval) {
    this.interval = interval;
  }

  public DateRange getDateRange() {
    return dateRange;
  }

  public void setDateRange(DateRange dateRange) {
    this.dateRange = dateRange;
  }

  public String getSymbol() {
    return symbol;
  }

  public void setSymbol( String symbol ) { this.symbol = symbol; }

  public List<TimeSeriesValue> getValues() {
    return values;
  }

  public void setValues(List<TimeSeriesValue> values) {
    this.values = values;
  }

  public String getLastRefreshed() {
    return lastRefreshed;
  }

  public void setLastRefreshed( String lastRefreshed ) { this.lastRefreshed = lastRefreshed; }

}
