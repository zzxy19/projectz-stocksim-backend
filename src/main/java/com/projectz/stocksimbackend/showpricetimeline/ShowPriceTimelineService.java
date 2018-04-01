package com.projectz.stocksimbackend.showpricetimeline;

public interface ShowPriceTimelineService {
  ShowPriceTimelineResponse handleShowPriceTimelineRequest(String symbol, int type, String interval, int dateRange, int startDate);
}
