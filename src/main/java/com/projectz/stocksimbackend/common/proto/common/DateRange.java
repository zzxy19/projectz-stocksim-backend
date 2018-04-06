package com.projectz.stocksimbackend.common.proto.common;

public enum DateRange {
  DAY(86400, 60 * 15, 27, "TIME_SERIES_INTRADAY", "15min"),
  WEEK(86400 * 7, 86400, 6, "TIME_SERIES_DAILY", null),
  MONTH(86400 * 30, 86400, 31, "TIME_SERIES_DAILY", null),
  YEAR(86400 * 7 * 52, 86400 * 7, 52, "TIME_SERIES_WEEKLY", null);

  public final int conversionFactor;
  public final int interval;
  public final int numEntry;
  public final String function;
  public final String intervalStr;

  DateRange(int conversionFactor, int interval, int numEntry, String function, String intervalStr) {
    this.conversionFactor = conversionFactor;
    this.interval = interval;
    this.numEntry = numEntry;
    this.function = function;
    this.intervalStr = intervalStr;
  }
}
