package com.projectz.stocksimbackend.common.proto.strategy;

import java.util.ArrayList;
import java.util.List;

public final class Condition {
  private List<Clause> clauses;

  public Condition() {
    this.clauses = new ArrayList<>();
  }

  public Condition(List<Clause> clauses) {
    this.clauses = clauses;
  }

  public void addClause(Clause clause) {
    clauses.add(clause);
  }

  public boolean satisfy(TimeSeriesAnalyzable analyzable) {
    for (Clause clause : clauses) {
      if (!clause.satisfy(analyzable)) {
        return false;
      }
    }
    return true;
  }
}
