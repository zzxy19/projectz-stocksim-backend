package com.projectz.stocksimbackend.common.proto;

public class TimeSeriesValue {
    private String time;
    private float open;
    private float high;
    private float low;
    private float close;
    private float volume;

    public TimeSeriesValue(
        String time, float open, float high, float low, float close, float volume) {
        this.time = time;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.volume = volume;
    }

    public String getTime() {
        return time;
    }

    public float getOpen() {
        return open;
    }

    public float getHigh() {
        return high;
    }

    public float getLow() {
        return low;
    }

    public float getClose() {
        return close;
    }

    public float getVolume() {
        return volume;
    }
}
