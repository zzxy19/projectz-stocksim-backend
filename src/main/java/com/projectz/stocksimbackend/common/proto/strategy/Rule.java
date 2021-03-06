package com.projectz.stocksimbackend.common.proto.strategy;

public final class Rule {
  private Condition condition;
  private AccountAction action;

  public Rule(Condition condition, AccountAction action) {
    this.condition = condition;
    this.action = action;
  }

  public boolean evaluate(TimeSeriesAnalyzable analyzable, TimeSeriesActionable actionable) {
    if (condition.satisfy(analyzable)) {
      actionable.executeAction(analyzable, action);
      return true;
    } else {
      return false;
    }
  }
}
