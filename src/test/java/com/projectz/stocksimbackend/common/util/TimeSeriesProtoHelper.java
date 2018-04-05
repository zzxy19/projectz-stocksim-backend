package com.projectz.stocksimbackend.common.util;

import com.projectz.stocksimbackend.common.proto.TimeSeriesProto;
import com.projectz.stocksimbackend.common.proto.TimeSeriesValue;

import java.sql.Time;
import java.util.ArrayList;

public class TimeSeriesProtoHelper {
  public static boolean equals(TimeSeriesProto one, TimeSeriesProto other) {
    if (!one.getSymbol().equals(other.getSymbol())) {
      return false;
    }

    if (!one.getInterval().equals(other.getInterval())) {
      return false;
    }

    if (!one.getLastRefreshed().equals(other.getLastRefreshed())) {
      return false;
    }

    ArrayList<TimeSeriesValue> oneValues = one.getValues();
    ArrayList<TimeSeriesValue> otherValues = other.getValues();


    if (oneValues == null && otherValues == null) {
      return true;
    }

    if (!(oneValues != null && otherValues != null)) {
      return false;
    }

    if (oneValues.size() != otherValues.size()) {
      return false;
    }

    for (int i = 0; i < oneValues.size(); i++) {
      if (!equals(oneValues.get(i), otherValues.get(i))) {
        return false;
      }
    }

    return true;
  }

  public static boolean equals(TimeSeriesValue one, TimeSeriesValue other) {
    if (!one.getTime().equals(other.getTime())) {
      return false;
    }

    if (one.getVolume() != other.getVolume()) {
      return false;
    }

    if (one.getClose() != other.getClose()) {
      return false;
    }

    if (one.getHigh() != other.getHigh()) {
      return false;
    }

    if (one.getLow() != other.getLow()) {
      return false;
    }

    if (one.getOpen() != other.getOpen()) {
      return false;
    }

    return true;
  }

}
