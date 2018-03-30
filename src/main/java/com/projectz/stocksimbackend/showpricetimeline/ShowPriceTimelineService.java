package com.projectz.stocksimbackend.showpricetimeline;

public interface ShowPriceTimelineService {
  ShowPriceTimelineResponse handleShowPriceTimelineRequest(String symbol, int dateRange, int startDate);
}
