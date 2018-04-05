package com.projectz.stocksimbackend.common.proto.strategy;

import com.projectz.stocksimbackend.common.proto.timeseries.TimeSeriesProto;

public abstract class Entity {
  public abstract double evaluate(TimeSeriesProto proto);
}
