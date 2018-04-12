package com.projectz.stocksimbackend.common.proto.strategy;

public class LargerClause extends Clause {
  private Entity left;
  private Entity right;

  public LargerClause(Entity left, Entity right) {
    this.left = left;
    this.right = right;
  }

  @Override
  public boolean satisfy(TimeSeriesAnalyzable analyzable) {
    Float leftValue = left.evaluate(analyzable);
    Float rightValue = right.evaluate(analyzable);
    return leftValue != null
      && rightValue != null
      && leftValue > rightValue;
  }
}
