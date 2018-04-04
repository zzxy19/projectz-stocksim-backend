package com.projectz.stocksimbackend.showpricetimeline;

import java.io.*;
import java.util.ArrayList;
import java.util.Map;
import java.nio.charset.StandardCharsets;

import com.projectz.stocksimbackend.common.Exception.BadRequestException;
import com.projectz.stocksimbackend.common.stockapi.TimeSeriesResponse;
import com.projectz.stocksimbackend.common.util.TimeSeriesConverter;
import com.sun.org.apache.bcel.internal.generic.ALOAD;
import org.springframework.beans.BeansException;
import org.springframework.stereotype.Service;
import com.projectz.stocksimbackend.common.stockapi.TimeSeriesResponseParser;
import com.projectz.stocksimbackend.common.stockapi.TimeSeriesIntradayResponse;
import com.projectz.stocksimbackend.common.stockapi.AlphaVantageAPIService;
import com.projectz.stocksimbackend.common.proto.TimeSeriesProto;
import com.projectz.stocksimbackend.common.proto.TimeSeriesValue;

@Service
public class ShowPriceTimelineServiceImpl implements ShowPriceTimelineService {
  private enum Type {
    DAY, WEEK, MONTH, YEAR, UNKOWN;
  }

  Type parseType(String typeString){
    switch (typeString) {
      case "day":
        return Type.DAY;
      case "week":
        return Type.WEEK;
      case "month":
        return Type.MONTH;
      case "year":
        return Type.YEAR;
      default:
        return Type.UNKOWN;
    }
  }


  @Override
  public ShowPriceTimelineResponse handleShowPriceTimelineRequest(
      String symbol, String type, int dateRange, int startDate){
    ShowPriceTimelineResponse response = new ShowPriceTimelineResponse();

    String rawResponse;
    switch (parseType(type)) {
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
        return null;
    }

    TimeSeriesResponse timeSeriesResponse = TimeSeriesResponseParser.parsetTimeSeriesResponse(rawResponse);
    TimeSeriesProto timeSeriesProto = TimeSeriesConverter.convertTimeSeriesResponseToProto(
        timeSeriesResponse);

    response.setTimeSeries(timeSeriesProto);
    return response;
  }
}