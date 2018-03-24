package com.projectz.stocksimbackend.common.stockapi;

import com.google.gson.Gson;

import java.io.InputStreamReader;

public final class TimeSeriesResponseParser {
  public static TimeSeriesIntradayResponse parseTimeSeriesIntradayResponse(
      InputStreamReader inputReader) {
    Gson gson = new Gson();
    TimeSeriesIntradayResponse response =
      gson.fromJson(inputReader, TimeSeriesIntradayResponse.class);
    return response;
  }
}
