package com.projectz.stocksimbackend.common.proto.strategy;

import com.projectz.stocksimbackend.common.proto.timeseries.TimeSeriesProto;

public class LargerClause extends Clause {
  private Entity left;
  private Entity right;

  public LargerClause(Entity left, Entity right) {
    this.left = left;
    this.right = right;
  }

  @Override
  public boolean satisfy(TimeSeriesProto proto) {
    return left.evaluate(proto) > right.evaluate(proto);
  }
}
