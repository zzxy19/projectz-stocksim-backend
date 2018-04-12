package com.projectz.stocksimbackend.common.proto.strategy;

import java.util.List;

public class Condition {
  private List<Clause> clauseList;

  public Condition(List<Clause> clauses) {
    clauseList = clauses;
  }

  public boolean satisfy(TimeSeriesAnalyzable analyzable) {
    for (Clause clause : clauseList) {
      if (!clause.satisfy(analyzable)) {
        return false;
      }
    }
    return true;
  }
}
