package com.projectz.stocksimbackend.common.stockapi;

import com.projectz.stocksimbackend.common.util.HttpsRequestBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
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

  public static String fetchPastDayTimeSeriesData(String symbol) {
    return getTimeSeriesData("TIME_SERIES_INTRADAY", symbol, "15min");
  }

  private static String getTimeSeriesData(String function, String symbol, String interval) {
    Map<String, String> paramBuilder = new TreeMap<>();
    paramBuilder.put(FUNCTION_FIELD, function);
    paramBuilder.put(SYMBOL_FIELD, symbol);
    paramBuilder.put(INTERVAL_FIELD, interval);
    paramBuilder.put(APIKEY_FIELD, API_KEY);

    logger.info("Initiated http request to Alpha Vantage stock api.");
    HttpsRequestBuilder requestBuilder;
    requestBuilder = new HttpsRequestBuilder(ALPHA_VANTAGE_DOMAIN_NAME, REQUEST, paramBuilder);
    try {
      BufferedReader httpResponseReader = requestBuilder.getResponseBufferedReader();
      String line;
      StringBuilder httpResponseBuilder = new StringBuilder();
      while ((line = httpResponseReader.readLine()) != null) {
        httpResponseBuilder.append(line);
      }
      httpResponseReader.close();
      logger.info("Finished fetching data from Alpha Vantage stock api.");
      return httpResponseBuilder.toString();
    } catch (IOException ex) {
      logger.error("Http connection to Alpha Vantage stock api failed", ex);
      throw new RuntimeException(ex);
    }
  }
}
