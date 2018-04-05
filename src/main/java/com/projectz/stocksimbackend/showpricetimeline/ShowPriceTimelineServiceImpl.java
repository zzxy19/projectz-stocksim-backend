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

    String rawResponse;
    switch (dateRange) {
      case DAY:
        rawResponse = AlphaVantageAPIService.fetchPastDayTimeSeriesData(symbol, "15min");
        break;
      case WEEK:
        rawResponse = AlphaVantageAPIService.fetchDailyTimeSeriesData(symbol);
        break;
      case MONTH:
        rawResponse = AlphaVantageAPIService.fetchWeeklyTimeSeriesData(symbol);
        break;
      case YEAR:
        rawResponse = AlphaVantageAPIService.fetchMonthlyTimeSeriesData(symbol);
        break;
      default:
        return response;
    }

    TimeSeriesResponse timeSeriesResponse = TimeSeriesResponseParser.parsetTimeSeriesResponse(rawResponse);
    TimeSeriesProto timeSeriesProto = TimeSeriesConverter.convertTimeSeriesResponseToProto(
        timeSeriesResponse.getMetadata(), timeSeriesResponse.getTimeSeries());

    response.setTimeSeries(timeSeriesProto);
    return response;
  }
}