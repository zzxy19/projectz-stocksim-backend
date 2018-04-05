package com.projectz.stocksimbackend.common.proto.common;

public enum DateRange {
  DAY(86400),
  WEEK(86400 * 7),
  MONTH(86400 * 30),
  YEAR(86400 * 365),
  FIVEYEAR(86400 * 365 * 5);

  public final long conversionFactor;

  DateRange(long conversionFactor) {
    this.conversionFactor = conversionFactor;
  }
}
