package com.projectz.stocksimbackend.common.proto.strategy;

import java.util.ArrayList;
import java.util.List;

public class Strategy {
  private List<Rule> rules;

  public Strategy() {
    rules = new ArrayList<>();
  }

  public Strategy(List<Rule> rules) {
    this.rules = rules;
  }

  public List<Rule> getRules() {
    return rules;
  }

  public void addRule(Rule rule) {
    rules.add(rule);
  }

  public boolean evaluate(TimeSeriesAnalyzable analyzable, TimeSeriesActionable actionable) {
    for (Rule rule : rules) {
      if (rule.evaluate(analyzable, actionable)) {
        return true;
      }
    }
    actionable.noAction(analyzable);
    return false;
  }
}
