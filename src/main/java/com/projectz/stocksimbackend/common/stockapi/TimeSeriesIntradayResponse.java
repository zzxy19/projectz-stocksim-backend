package com.projectz.stocksimbackend.common.stockapi;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class TimeSeriesIntradayResponse {
  public Map<String, Map<String, String>> getTimeSeries() {
    return timeSeries;
  }

  public void setTimeSeries(Map<String, Map<String, String>> timeSeries) {
    this.timeSeries = timeSeries;
  }

  public Map<String, String> getMetadata() {
    return metadata;
  }

  public void setMetadata(Map<String, String> metadata) {
    this.metadata = metadata;
  }

  @SerializedName("Meta Data")
  private Map<String, String> metadata;

  @SerializedName("Time Series (1min)")
  Map<String, Map<String, String>> timeSeries;
}
