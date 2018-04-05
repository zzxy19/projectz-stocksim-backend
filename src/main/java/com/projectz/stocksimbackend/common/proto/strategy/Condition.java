package com.projectz.stocksimbackend.common.proto.strategy;


import com.projectz.stocksimbackend.common.proto.timeseries.TimeSeriesProto;

import java.util.List;

public class Condition {
  private List<Clause> clauseList;

  public Condition(List<Clause> clauses) {
    clauseList = clauses;
  }

  public boolean satisfy(TimeSeriesProto proto) {
    for (Clause clause : clauseList) {
      if (!clause.satisfy(proto)) {
        return false;
      }
    }
    return true;
  }
}
