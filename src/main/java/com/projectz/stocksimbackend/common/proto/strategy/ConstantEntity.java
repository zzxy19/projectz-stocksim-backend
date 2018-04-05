package com.projectz.stocksimbackend.common.proto.strategy;

import com.projectz.stocksimbackend.common.proto.timeseries.TimeSeriesProto;

public class ConstantEntity extends Entity {
  private double value;

  public ConstantEntity(double value) {
    this.value = value;
  }

  @Override
  public double evaluate(TimeSeriesProto proto) {
    return value;
  }
}
