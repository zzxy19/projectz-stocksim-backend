package com.projectz.stocksimbackend.common.proto.strategy;

public class DiffEntity extends Entity {
  private final Entity origin;
  private final Entity subtraction;

  public DiffEntity(Entity origin, Entity subtraction) {
    this.origin = origin;
    this.subtraction = subtraction;
  }

  @Override
  public Float evaluate(TimeSeriesAnalyzable analyzable) {
    Float originalValue = origin.evaluate(analyzable);
    Float subtractionValue = subtraction.evaluate(analyzable);
    if (originalValue == null) {
      return null;
    }
    if (subtractionValue == null) {
      return originalValue;
    }
    return originalValue - subtractionValue;
  }
}
