package com.projectz.stocksimbackend.showpricetimeline;

import com.projectz.stocksimbackend.common.proto.timeseries.TimeSeriesProto;

public final class ShowPriceTimelineResponse {
  private TimeSeriesProto timeSeries;

  public TimeSeriesProto getTimeSeries() { return timeSeries; }

  public void setTimeSeries(TimeSeriesProto timeSeries) { this.timeSeries = timeSeries; }
}
