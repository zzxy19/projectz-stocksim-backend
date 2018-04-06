package com.projectz.stocksimbackend.common.stockapi;

import com.projectz.stocksimbackend.common.proto.common.DateRange;
import com.projectz.stocksimbackend.common.proto.timeseries.TimeSeriesProto;
import com.projectz.stocksimbackend.common.util.HttpsRequestBuilder;
import com.projectz.stocksimbackend.common.util.TimeSeriesConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;

public class AlphaVantageAPIService {
  private static final Logger logger = LoggerFactory.getLogger(AlphaVantageAPIService.class);

  private static final String ALPHA_VANTAGE_DOMAIN_NAME = "www.alphavantage.co";
  private static final String REQUEST = "query";
  private static final String FUNCTION_FIELD = "function";
  private static final String INTERVAL_FIELD = "interval";
  private static final String SYMBOL_FIELD = "symbol";
  private static final String APIKEY_FIELD = "apikey";
  private static final String API_KEY = "AU3HU25G1MNY2SJL";

  private AlphaVantageAPIService() {}

  public static TimeSeriesProto fetchTimeSeriesData(DateRange dateRange, String symbol) {
    TimeSeriesProto timeSeriesProto =
        TimeSeriesConverter.convertTimeSeriesResponseToProto(
            getTimeSeriesResponse(dateRange.function, symbol, dateRange.intervalStr));
    timeSeriesProto.setDateRange(dateRange);
    timeSeriesProto.setInterval(dateRange.interval);
    trimTimeSeriesProto(timeSeriesProto);
    return timeSeriesProto;
  }

  private static void trimTimeSeriesProto(TimeSeriesProto timeSeriesProto) {
    DateRange dateRange = timeSeriesProto.getDateRange();
    int numValues = timeSeriesProto.getValues().size();
    int endIndex = Math.min(dateRange.numEntry, numValues);
    timeSeriesProto.setValues(
      timeSeriesProto.getValues().subList(0, endIndex));
  }

  private static TimeSeriesResponse getTimeSeriesResponse(
    String function, String symbol, String interval) {
    Map<String, String> paramBuilder = new TreeMap<>();
    paramBuilder.put(FUNCTION_FIELD, function);
    paramBuilder.put(SYMBOL_FIELD, symbol);
    if (interval != null) {
      paramBuilder.put(INTERVAL_FIELD, interval);
    }
    paramBuilder.put(APIKEY_FIELD, API_KEY);

    logger.info("Initiated http request to Alpha Vantage stock api.");
    HttpsRequestBuilder requestBuilder;
    requestBuilder = new HttpsRequestBuilder(ALPHA_VANTAGE_DOMAIN_NAME, REQUEST, paramBuilder);
    try {
      InputStreamReader httpResponseReader = requestBuilder.getResponseBufferedReader();
      TimeSeriesResponse timeSeriesResponse =
        TimeSeriesResponseParser.parseTimeSeriesResponse(httpResponseReader);
      httpResponseReader.close();
      logger.info("Finished fetching data from Alpha Vantage stock api.");
      return timeSeriesResponse;
    } catch (IOException ex) {
      logger.error("Http connection to Alpha Vantage stock api failed", ex);
      throw new RuntimeException(ex);
    }
  }
}
