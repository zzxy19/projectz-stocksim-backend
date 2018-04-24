package com.projectz.stocksimbackend.common.proto.strategy;

import java.util.List;

public class StrategyInput {
  List<RuleInput> rules;

  class RuleInput {
    ConditionInput condition;
    ActionInput action;
  }

  class ConditionInput {
    List<ClauseInput> clauses;
  }

  class ActionInput {
    ActionType actionType;
    AmountType amountType;
    Float amount;
  }

  class ClauseInput {
    ClauseType clauseType;
    EntityInput left;
    EntityInput right;
  }

  class EntityInput {
    EntityType entityType;
    Float value;
    Float amount;
    Float coefficient;
    EntityInput origin;
    EntityInput subtraction;
  }

  private enum ClauseType {
    EQUAL,
    LARGER
  }

  private enum EntityType {
    CONSTANT,
    PRICE,
    DIFF
  }
}
