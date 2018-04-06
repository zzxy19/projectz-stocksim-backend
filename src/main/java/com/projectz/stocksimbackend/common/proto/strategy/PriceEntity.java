package com.projectz.stocksimbackend.common.proto.strategy;

import com.projectz.stocksimbackend.common.proto.timeseries.TimeSeriesProto;

public class PriceEntity extends Entity {
  private int amount;

  public PriceEntity(int amount) {
    this.amount = amount;
  }

  @Override
  public double evaluate(TimeSeriesProto proto) {
    int numEntry = proto.getValues().size();
    int indexBackward = Math.min(amount, numEntry);
    return proto.getValues().get(indexBackward).getClose();
  }
}
