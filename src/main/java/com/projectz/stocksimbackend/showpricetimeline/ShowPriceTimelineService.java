package com.projectz.stocksimbackend.showpricetimeline;

public interface ShowPriceTimelineService {
  ShowPriceTimelineResponse handleShowPriceTimelineRequest(
      String symbol, String interval, int dateRange, int startDate);
}
