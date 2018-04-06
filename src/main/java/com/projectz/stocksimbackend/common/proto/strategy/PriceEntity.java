package com.projectz.stocksimbackend.common.proto.strategy;

import com.projectz.stocksimbackend.common.proto.common.DateRange;
import com.projectz.stocksimbackend.common.proto.timeseries.TimeSeriesProto;
import com.projectz.stocksimbackend.common.proto.timeseries.TimeSeriesValue;

import java.util.ArrayList;
import java.util.List;

public class PriceEntity extends Entity {
  private long amount;
  private DateRange dateRange;

  public PriceEntity(long amount, DateRange dateRange) {
    this.amount = amount;
    this.dateRange = dateRange;
  }

  @Override
  public double evaluate(TimeSeriesProto proto) {
    long secondsAgo = amount * dateRange.conversionFactor;
    long indexBackward = secondsAgo / proto.getInterval();
    List<TimeSeriesValue> values = proto.getValues();
    if (indexBackward >= values.size()) {
      return -1;
    }
    return 0;
  }
}
