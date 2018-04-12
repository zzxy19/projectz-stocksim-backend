package com.projectz.stocksimbackend.common.proto.strategy;

public class ConstantEntity extends Entity {
  private float value;

  public ConstantEntity(float value) {
    this.value = value;
  }

  @Override
  public Float evaluate(TimeSeriesAnalyzable unusedAnalyzable) {
    return value;
  }
}
