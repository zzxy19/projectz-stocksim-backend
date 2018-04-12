package com.projectz.stocksimbackend.common.proto.strategy;

public class EqualClause extends Clause {
  private Entity left;
  private Entity right;

  public EqualClause(Entity left, Entity right) {
    this.left = left;
    this.right = right;
  }

  @Override
  public boolean satisfy(TimeSeriesAnalyzable analyzable) {
    Float leftValue = left.evaluate(analyzable);
    Float rightValue = right.evaluate(analyzable);
    return leftValue != null
        && rightValue != null
        && leftValue.equals(rightValue);
  }
}
