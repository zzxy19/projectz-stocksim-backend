package com.projectz.stocksimbackend.common.proto.strategy;

import java.util.List;

public class StrategyInput {
  public List<RuleInput> rules;

  public Strategy toStrategy() {
    Strategy strategy = new Strategy();
    for (RuleInput ruleInput : rules) {
      strategy.addRule(ruleInput.toRule());
    }
    return strategy;
  }

  public class RuleInput {
    ConditionInput condition;
    ActionInput action;

    public Rule toRule() {
      return new Rule(condition.toCondition(), action.toAction());
    }
  }

  public class ConditionInput {
    List<ClauseInput> clauses;

    public Condition toCondition() {
      Condition condition = new Condition();
      for (ClauseInput clauseInput : clauses) {
        condition.addClause(clauseInput.toClause());
      }
      return condition;
    }
  }

  public class ActionInput {
    ActionType actionType;
    AmountType amountType;
    Float amount;

    public AccountAction toAction() {
      return new AccountAction(actionType, amountType, amount);
    }
  }

  public class ClauseInput {
    ClauseType clauseType;
    EntityInput left;
    EntityInput right;

    private void validateClauseInput() {
      assert (left != null && right != null);
      left.validateEntityInput();
      right.validateEntityInput();
    }

    public Clause toClause() {
      validateClauseInput();
      return inputToClause(this);
    }
  }

  public class EntityInput {
    EntityType entityType;
    Float value;
    Integer amount;
    Float coefficient;
    EntityInput origin;
    EntityInput subtraction;

    private void validateEntityInput() {
      switch (entityType) {
        case CONSTANT:
          assert (value != null);
          break;

        case PRICE:
          assert (amount != null && coefficient != null);
          break;

        case DIFF:
          assert (origin != null && subtraction != null);
          origin.validateEntityInput();
          subtraction.validateEntityInput();
          break;

        default:
          throw new RuntimeException("Unknown entity type.");
      }
    }

    public Entity toEntity() {
      validateEntityInput();
      return inputToEntity(this);
    }
  }

  public enum ClauseType {
    EQUAL,
    LARGER
  }

  public enum EntityType {
    CONSTANT,
    PRICE,
    DIFF
  }

  private static Clause inputToClause(ClauseInput input) {
    switch (input.clauseType) {
      case EQUAL:
        return new EqualClause(inputToEntity(input.left), inputToEntity(input.right));

      case LARGER:
        return new LargerClause(inputToEntity(input.left), inputToEntity(input.right));

      default:
        throw new RuntimeException("Unknown clause type.");
    }
  }

  private static Entity inputToEntity(EntityInput input) {
    switch (input.entityType) {
      case PRICE:
        if (input.coefficient == null) {
          return new PriceEntity(input.amount);
        } else {
          return new PriceEntity(input.amount, input.coefficient);
        }

      case CONSTANT:
        return new ConstantEntity(input.value);

      case DIFF:
        return new DiffEntity(inputToEntity(input.origin), inputToEntity(input.subtraction));

      default:
        throw new RuntimeException("Unknown entity type.");
    }
  }
}
