package com.projectz.stocksimbackend.common.stockapi;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class TimeSeriesResponse {
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

  @SerializedName(value="Time Series (1min)", alternate={"Time Series (5min)",
      "Time Series (15min)", "Time Series (30min)", "Time Series (60min)", "Time Series (daily)",
      "Time Series (Daily)", "Weekly Time Series", "Monthly Time Series"})
  Map<String, Map<String, String>> timeSeries;
}
