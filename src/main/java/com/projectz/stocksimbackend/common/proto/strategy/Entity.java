package com.projectz.stocksimbackend.common.proto.strategy;

public abstract class Entity {
  public abstract Float evaluate(TimeSeriesAnalyzable analyzable);
}
