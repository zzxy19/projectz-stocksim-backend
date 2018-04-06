package com.projectz.stocksimbackend.common.stockapi;

import com.google.gson.Gson;

import java.io.InputStreamReader;

public final class TimeSeriesResponseParser {
  public static TimeSeriesResponse parseTimeSeriesResponse(
    InputStreamReader inputReader) {
    Gson gson = new Gson();
    TimeSeriesResponse response =
      gson.fromJson(inputReader, TimeSeriesResponse.class);
    return response;
  }
}
