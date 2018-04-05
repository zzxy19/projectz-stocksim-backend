package com.projectz.stocksimbackend.common.proto.strategy;

import com.projectz.stocksimbackend.common.proto.timeseries.TimeSeriesProto;

public abstract class Clause {
  public abstract boolean satisfy(TimeSeriesProto proto);
}
