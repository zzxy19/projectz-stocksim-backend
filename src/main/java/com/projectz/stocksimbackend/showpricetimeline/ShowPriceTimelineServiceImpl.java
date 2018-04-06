package com.projectz.stocksimbackend.showpricetimeline;

import com.projectz.stocksimbackend.common.proto.common.DateRange;
import com.projectz.stocksimbackend.common.stockapi.TimeSeriesResponse;
import com.projectz.stocksimbackend.common.util.TimeSeriesConverter;
import org.springframework.stereotype.Service;
import com.projectz.stocksimbackend.common.stockapi.TimeSeriesResponseParser;
import com.projectz.stocksimbackend.common.stockapi.AlphaVantageAPIService;
import com.projectz.stocksimbackend.common.proto.timeseries.TimeSeriesProto;

@Service
public class ShowPriceTimelineServiceImpl implements ShowPriceTimelineService {
  @Override
  public ShowPriceTimelineResponse handleShowPriceTimelineRequest(
    String symbol, DateRange dateRange, int startDate) {
    ShowPriceTimelineResponse response = new ShowPriceTimelineResponse();

    TimeSeriesProto timeSeriesProto =
      AlphaVantageAPIService.fetchTimeSeriesData(dateRange, symbol);

    response.setTimeSeries(timeSeriesProto);
    return response;
  }
}