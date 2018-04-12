package com.projectz.stocksimbackend.strategy;

import com.projectz.stocksimbackend.common.proto.strategy.*;
import com.projectz.stocksimbackend.common.proto.timeseries.TimeSeriesValue;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class PrototypeStrategyTest {
  private TimeSeriesAnalyzable sampleIntradayAnalyzable;

  @Before
  public void setUp() {
    List<TimeSeriesValue> values = new ArrayList<>();
    // new TimeSeriesValue(DATE, OPEN, HIGH, LOW, CLOSE, VOLUME)
    values.add(new TimeSeriesValue("2018-01-05", 50, 50, 20, 25, 1000));
    values.add(new TimeSeriesValue("2018-01-04", 30, 60, 20, 50, 2000));
    values.add(new TimeSeriesValue("2018-01-03", 50, 60, 20, 30, 100));
    values.add(new TimeSeriesValue("2018-01-02", 80, 80, 40, 50, 200));
    values.add(new TimeSeriesValue("2018-01-01", 100, 120, 80, 80, 4000));
    sampleIntradayAnalyzable = new TimeSeriesAnalyzable("TSLA", values);
  }

  @Test
  public void testPriceEntityEvaluation() {
    PriceEntity priceEntity = new PriceEntity(0);
    assert(priceEntity.evaluate(sampleIntradayAnalyzable) == (float) 80);

    sampleIntradayAnalyzable.goToNextDay();
    assert(priceEntity.evaluate(sampleIntradayAnalyzable) == (float) 50);
  }

  @Test
  public void testGoToNextDayFailAfterFourTimes() {
    for (int i = 0; i < 4; i++) {
      assert(sampleIntradayAnalyzable.goToNextDay());
    }
    assert(!sampleIntradayAnalyzable.goToNextDay());
  }

  @Test
  public void testLargerClauseEvaluation_constantEntity() {
    Entity priceAtToday = new PriceEntity(0);
    Entity myCost = new ConstantEntity(10);
    Clause priceTodayHigherThanMyCost = new LargerClause(priceAtToday, myCost);
    assert(priceTodayHigherThanMyCost.satisfy(sampleIntradayAnalyzable));
  }

  @Test
  public void testLargerClauseEvaluation_yesterdayOutOfRange() {
    Entity priceAtToday = new PriceEntity(0);
    Entity priceAtYesterday = new PriceEntity(1);
    Clause priceTodayLargerThanYesterday = new LargerClause(priceAtToday, priceAtYesterday);
    assert(!priceTodayLargerThanYesterday.satisfy(sampleIntradayAnalyzable));
  }

  @Test
  public void testLargerClauseEvaluation_yesterdayInRange() {
    // Move two days up to 2018-01-03
    assert(sampleIntradayAnalyzable.goToNextDay());
    assert(sampleIntradayAnalyzable.goToNextDay());
    Entity priceAtToday = new PriceEntity(0); // 2018-01-03: $30
    Entity priceAtYesterday = new PriceEntity(1); // 2018-01-02: $50
    Clause priceYesterdayLargerThanToday = new LargerClause(priceAtYesterday, priceAtToday);
    assert(priceYesterdayLargerThanToday.satisfy(sampleIntradayAnalyzable));
  }

  @Test
  public void testLargerClauseEvaluation_priceTodayDropMoreThanTenPercentFromYesterday() {
    // Move two days up to 2018-01-03
    assert(sampleIntradayAnalyzable.goToNextDay());
    assert(sampleIntradayAnalyzable.goToNextDay());
    Entity priceAtToday = new PriceEntity(0); // 2018-01-03: $30
    Entity priceAtYesterday = new PriceEntity(1); // 2018-01-02: $50
    Entity tenPercentOfYesterday = new PriceEntity(1, 0.1);
    Entity dropTenPercentFromYesterday = new DiffEntity(priceAtYesterday, tenPercentOfYesterday);
    Clause priceTodayDropMoreThanTenPercent =
      new LargerClause(dropTenPercentFromYesterday, priceAtToday);
    assert(priceTodayDropMoreThanTenPercent.satisfy(sampleIntradayAnalyzable));
  }

  @Test
  public void testLargerClauseEvaluation_priceTodayDropLessThanHalfFromYesterday() {
    // Move two days up to 2018-01-03
    assert(sampleIntradayAnalyzable.goToNextDay());
    assert(sampleIntradayAnalyzable.goToNextDay());
    Entity priceAtToday = new PriceEntity(0); // 2018-01-03: $30
    Entity priceAtYesterday = new PriceEntity(1); // 2018-01-02: $50
    Entity halfOfYesterday = new PriceEntity(1, 0.5);
    Entity dropHalfFromYesterday = new DiffEntity(priceAtYesterday, halfOfYesterday);
    Clause priceTodayDropMoreThanHalf =
      new LargerClause(dropHalfFromYesterday, priceAtToday);
    assert(!priceTodayDropMoreThanHalf.satisfy(sampleIntradayAnalyzable));
  }
}
