package com.projectz.stocksimbackend.showpricetimeline;

import java.io.*;
import java.util.ArrayList;
import java.util.Map;
import java.nio.charset.StandardCharsets;

import org.springframework.stereotype.Service;
import com.projectz.stocksimbackend.common.stockapi.TimeSeriesResponseParser;
import com.projectz.stocksimbackend.common.stockapi.TimeSeriesIntradayResponse;
import com.projectz.stocksimbackend.common.stockapi.AlphaVantageAPIService;
import com.projectz.stocksimbackend.common.proto.TimeSeriesProto;
import com.projectz.stocksimbackend.common.proto.TimeSeriesValue;

@Service
public class ShowPriceTimelineServiceImpl implements ShowPriceTimelineService {
  //meta date
  private static final String INFO_FIELD = "1. Information";
  private static final String SYMBOL_FIELD = "2. Symbol";
  private static final String LR_FIELD = "3. Last Refreshed";
  private static final String INTERVAL_FIELD = "4. Interval";
  private static final String OS_FIELD = "5. Output Size";
  //time series
  private static final String OPEN_FIELD = "1. open";
  private static final String HIGH_FIELD = "2. high";
  private static final String LOW_FIELD = "3. low";
  private static final String CLOSE_FIELD = "4. close";
  private static final String VOLUME_FIRLD = "5. volume";

  @Override
  public ShowPriceTimelineResponse handleShowPriceTimelineRequest(String symbol, int dateRange, int startDate) {
    ShowPriceTimelineResponse response = new ShowPriceTimelineResponse();

    String rawString = AlphaVantageAPIService.fetchPastDayTimeSeriesData("MSFT", "1min");
    InputStream inputStream = new ByteArrayInputStream(rawString.getBytes(StandardCharsets.UTF_8));
    InputStreamReader reader = new InputStreamReader(inputStream);

    TimeSeriesIntradayResponse rawResponse =
        TimeSeriesResponseParser.parseTimeSeriesIntradayResponse(reader);
    Map<String, Map<String, String>> timeSeries = rawResponse.getTimeSeries();
    Map<String, String> metaData = rawResponse.getMetadata();

    TimeSeriesProto timeSeriesProto = new TimeSeriesProto();
    //set up meta date
    timeSeriesProto.setSymbol(metaData.get(SYMBOL_FIELD));
//    timeSeriesProto.setInterval(Integer.parseInt(metaData.get(INTERVAL_FIELD))); TODO: convert time String to Int (unit second)
//    timeSeriesProto.setLastRefreshed(metaData.get(LR_FIELD))); TODO: convert String to Instant.

    ArrayList<TimeSeriesValue> timeSeriesValues = new ArrayList<>();
    //set up timeSeries values
    for(Map.Entry<String, Map<String, String>> entry : timeSeries.entrySet()){
      TimeSeriesValue tsv = new TimeSeriesValue();
      tsv.setTime(entry.getKey());
      Map<String, String> value = entry.getValue();
      tsv.setClose(Float.parseFloat(value.get(CLOSE_FIELD)));
      tsv.setHigh(Float.parseFloat(value.get(HIGH_FIELD)));
      tsv.setLow(Float.parseFloat(value.get(LOW_FIELD)));
      tsv.setOpen(Float.parseFloat(value.get(OPEN_FIELD)));
      tsv.setVolume(Float.parseFloat(value.get(VOLUME_FIRLD)));
      timeSeriesValues.add(tsv);
    }
    timeSeriesProto.setValues(timeSeriesValues);

    response.setTimeSeries((timeSeriesProto));
    return response;
  }
}