package com.projectz.stocksimbackend.showpricetimeline;

import com.projectz.stocksimbackend.common.proto.common.DateRange;

public interface ShowPriceTimelineService {
  ShowPriceTimelineResponse handleShowPriceTimelineRequest(
    String symbol, DateRange dateRange, int startDate);
}
