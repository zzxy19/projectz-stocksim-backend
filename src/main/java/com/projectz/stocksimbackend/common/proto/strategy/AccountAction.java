package com.projectz.stocksimbackend.common.proto.strategy;

public class AccountAction {
  private final ActionType actionType;
  private final AmountType amountType;
  private final int coefficient;
  private final float amount;

  public AccountAction(ActionType actionType, AmountType amountType, float amount) {
    this.actionType = actionType;
    if (actionType == ActionType.BUY) {
      coefficient = 1;
    } else {
      coefficient = -1;
    }
    this.amountType = amountType;
    if (amountType == AmountType.SHARE) {
      amount = (float) (int) amount;
    }
    this.amount = amount;
  }

  public ActionType getActionType() {
    return actionType;
  }

  public AmountType getAmountType() {
    return amountType;
  }

  public int getCoefficient() {
    return coefficient;
  }

  public float getAmount() {
    return amount;
  }
}
