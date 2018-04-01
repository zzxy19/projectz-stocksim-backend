package com.projectz.stocksimbackend.showpricetimeline;

import java.io.*;
import java.util.ArrayList;
import java.util.Map;
import java.nio.charset.StandardCharsets;

import com.projectz.stocksimbackend.common.stockapi.TimeSeriesResponse;
import com.projectz.stocksimbackend.common.util.TimeSeriesConverter;
import com.sun.org.apache.bcel.internal.generic.ALOAD;
import org.springframework.stereotype.Service;
import com.projectz.stocksimbackend.common.stockapi.TimeSeriesResponseParser;
import com.projectz.stocksimbackend.common.stockapi.TimeSeriesIntradayResponse;
import com.projectz.stocksimbackend.common.stockapi.AlphaVantageAPIService;
import com.projectz.stocksimbackend.common.proto.TimeSeriesProto;
import com.projectz.stocksimbackend.common.proto.TimeSeriesValue;

@Service
public class ShowPriceTimelineServiceImpl implements ShowPriceTimelineService {

  @Override
  public ShowPriceTimelineResponse handleShowPriceTimelineRequest(
      String symbol, int type, String interval, int dateRange, int startDate) {
    ShowPriceTimelineResponse response = new ShowPriceTimelineResponse();

    String rawResponse;
    switch (type) {
      case 0:
        rawResponse = AlphaVantageAPIService.fetchPastDayTimeSeriesData(symbol, interval);
        break;
      case 1:
        rawResponse = AlphaVantageAPIService.fetchDailyTimeSeriesData(symbol);
        break;
      case 2:
        rawResponse = AlphaVantageAPIService.fetchWeeklyTimeSeriesData(symbol);
        break;
      case 3:
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