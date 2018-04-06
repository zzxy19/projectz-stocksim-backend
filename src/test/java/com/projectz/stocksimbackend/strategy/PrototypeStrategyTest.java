package com.projectz.stocksimbackend.strategy;

import com.projectz.stocksimbackend.common.proto.strategy.*;
import com.projectz.stocksimbackend.common.proto.timeseries.TimeSeriesProto;
import com.projectz.stocksimbackend.common.sample.SampleDataReader;
import org.junit.Before;
import org.junit.Test;

public class PrototypeStrategyTest {
  private TimeSeriesProto sampleIntradayData;
  /**
   tsv0.setTime("2018-03-23 16:00:00");
   tsv0.setVolume(10674681);
   tsv0.setOpen((float) 88.1000);
   tsv0.setClose((float) 87.1800);
   tsv0.setHigh((float) 88.1400);
   tsv0.setLow((float) 87.0800);

   tsv1.setTime("2018-03-23 15:45:00");
   tsv1.setVolume(2363268);
   tsv1.setOpen((float) 88.4300);
   tsv1.setClose((float) 88.0900);
   tsv1.setHigh((float) 88.4700);
   tsv1.setLow((float) 88.0000);
   */

  @Before
  public void setUp() {
    sampleIntradayData = SampleDataReader.getExpectedTimeSeriesProtoFromIntradayData();
  }

  @Test
  public void testPriceEntityEvaluation() {
    float expectedValue = (float) 88.0900;
    PriceEntity priceEntity = new PriceEntity(1);
    assert(priceEntity.evaluate(sampleIntradayData) == expectedValue);
  }

  @Test
  public void testEqualClauseEvaluation() {
    Entity priceAtToday = new PriceEntity(0);
    Entity priceAtYesterday = new PriceEntity(1);
    Clause priceTodayEqualsYesterday = new EqualClause(priceAtToday, priceAtYesterday);
    assert(!priceTodayEqualsYesterday.satisfy(sampleIntradayData));
  }

  @Test
  public void testLargerClauseEvaluation() {
    Entity priceAtToday = new PriceEntity(0);
    Entity priceAtYesterday = new PriceEntity(1);
    Clause priceTodayLargerThanYesterday = new LargerClause(priceAtToday, priceAtYesterday);
    assert(!priceTodayLargerThanYesterday.satisfy(sampleIntradayData));
    Entity EightyDollars = new ConstantEntity(80);
    Clause priceTodayHigherThanEightyDollars = new LargerClause(priceAtToday, EightyDollars);
    assert(priceTodayHigherThanEightyDollars.satisfy(sampleIntradayData));
  }
}
