package com.projectz.stocksimbackend.common.proto.strategy;

import com.projectz.stocksimbackend.common.proto.timeseries.TimeSeriesValue;

public class PriceEntity extends Entity {
  private final int amount;
  private final float coefficient;

  public PriceEntity(int amount) {
    this.amount = amount;
    this.coefficient = (float) 1.0;
  }

  public PriceEntity(int amount, float coefficient) {
    this.amount = amount;
    this.coefficient = coefficient;
  }

  public PriceEntity(int amount, double coefficient) {
    this.amount = amount;
    this.coefficient = (float) coefficient;
  }

  @Override
  public Float evaluate(TimeSeriesAnalyzable analyzable) {
    TimeSeriesValue value = analyzable.getPreviousValue(amount);
    if (value == null) {
      return null;
    }
    return value.getClose() * coefficient;
  }
}
