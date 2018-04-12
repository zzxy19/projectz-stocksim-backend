package com.projectz.stocksimbackend.common.proto.strategy;

public abstract class Clause {
  public abstract boolean satisfy(TimeSeriesAnalyzable analyzable);
}
