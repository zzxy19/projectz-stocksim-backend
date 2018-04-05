package com.projectz.stocksimbackend.common.util;

import com.projectz.stocksimbackend.common.proto.timeseries.TimeSeriesProto;
import com.projectz.stocksimbackend.common.proto.timeseries.TimeSeriesValue;

import java.util.ArrayList;
import java.util.Map;

public class TimeSeriesConverter {
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

  public static TimeSeriesProto convertTimeSeriesResponseToProto(
      Map<String, String> metaData, Map<String, Map<String, String>> timeSeries) {
    TimeSeriesProto timeSeriesProto = new TimeSeriesProto();

    //set up meta date
    timeSeriesProto.setSymbol(metaData.get(SYMBOL_FIELD));
    // TODO: fix this
    // timeSeriesProto.setInterval(metaData.get(INTERVAL_FIELD));
    timeSeriesProto.setLastRefreshed(metaData.get(LR_FIELD));

    ArrayList<TimeSeriesValue> timeSeriesValues = new ArrayList<>();
    //set up timeSeries values
    for(Map.Entry<String, Map<String, String>> entry : timeSeries.entrySet()){
      TimeSeriesValue timeSeriesValue = new TimeSeriesValue();
      timeSeriesValue.setTime(entry.getKey());
      Map<String, String> value = entry.getValue();
      timeSeriesValue.setClose(Float.parseFloat(value.get(CLOSE_FIELD)));
      timeSeriesValue.setHigh(Float.parseFloat(value.get(HIGH_FIELD)));
      timeSeriesValue.setLow(Float.parseFloat(value.get(LOW_FIELD)));
      timeSeriesValue.setOpen(Float.parseFloat(value.get(OPEN_FIELD)));
      timeSeriesValue.setVolume(Float.parseFloat(value.get(VOLUME_FIRLD)));
      timeSeriesValues.add(timeSeriesValue);
    }
    timeSeriesProto.setValues(timeSeriesValues);

    return timeSeriesProto;
  }

}
