package com.projectz.stocksimbackend.strategy;

import com.projectz.stocksimbackend.common.proto.strategy.Clause;
import com.projectz.stocksimbackend.common.proto.strategy.EqualClause;
import com.projectz.stocksimbackend.common.proto.strategy.LargerClause;
import com.projectz.stocksimbackend.common.proto.strategy.PriceEntity;
import com.projectz.stocksimbackend.common.proto.timeseries.TimeSeriesProto;
import com.projectz.stocksimbackend.common.sample.SampleDataReader;
import org.junit.Before;
import org.junit.Test;

public class PrototypeStrategyTest {
  private static final TimeSeriesProto SAMPLE_DATA =
    SampleDataReader.readTimeSeriesProtoFromIntradayData();
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
  public void setUp() {}

  @Test
  public void testPriceEntityEvaluation() {
    float expectedValue = (float) 88.0900;
    PriceEntity priceEntity = new PriceEntity(1);
    assert(priceEntity.evaluate(SAMPLE_DATA) == expectedValue);
  }

  @Test
  public void testEqualClauseEvaluation() {
    PriceEntity priceAtToday = new PriceEntity(0);
    PriceEntity priceAtYesterday = new PriceEntity(1);
    Clause priceTodayEqualsYesterday = new EqualClause(priceAtToday, priceAtYesterday);
    assert(!priceTodayEqualsYesterday.satisfy(SAMPLE_DATA));
  }

  @Test
  public void testLargerClauseEvaluation() {
    PriceEntity priceAtToday = new PriceEntity(0);
    PriceEntity priceAtYesterday = new PriceEntity(1);
    Clause priceTodayLargerThanYesterday = new LargerClause(priceAtToday, priceAtYesterday);
    assert(!priceTodayLargerThanYesterday.satisfy(SAMPLE_DATA));
  }
}
