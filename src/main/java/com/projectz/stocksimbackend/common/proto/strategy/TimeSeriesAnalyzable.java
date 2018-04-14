package com.projectz.stocksimbackend.common.proto.strategy;

import com.projectz.stocksimbackend.common.proto.timeseries.TimeSeriesValue;

import java.util.List;

public final class TimeSeriesAnalyzable {
  private final String symbol;
  private final List<TimeSeriesValue> values;
  private int currentIndex;
  private boolean exhausted;

  public String getSymbol() {
    return symbol;
  }

  public int getCurrentIndex() {
    return currentIndex;
  }

  public boolean goToNextDay() {
    currentIndex--;
    if (currentIndex < 0) {
      exhausted = true;
    }
    return !exhausted;
  }

  public float getCurrentPrice() {
    try {
      return getCurrentValue().getClose();
    } catch (NullPointerException ex) {
      throw new RuntimeException(
        "Should not call getCurrentPrice when analyzable is exhausted");
    }
  }

  public TimeSeriesValue getCurrentValue() {
    if (exhausted) {
      return null;
    }
    return values.get(currentIndex);
  }

  public TimeSeriesValue getPreviousValue(int step) {
    if (step < 0 || exhausted) {
      return null;
    }
    int index = currentIndex + step;
    if (index >= values.size()) {
      return null;
    }
    return values.get(index);
  }

  public TimeSeriesAnalyzable(String symbol, List<TimeSeriesValue> values) {
    this.symbol = symbol;
    this.values = values;
    currentIndex = values.size() - 1;
    exhausted = values.isEmpty();
  }
}
