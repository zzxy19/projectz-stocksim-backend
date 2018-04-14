package com.projectz.stocksimbackend.common.proto.strategy;

public final class AccountSummary {
  private final float initialCashBalance;

  private float currentCashBalance;
  private float currentStockBalance;
  private int currentStockShare;

  public AccountSummary(float initialBalance) {
    this.initialCashBalance = initialBalance;
    currentCashBalance = initialBalance;
    currentStockBalance = 0;
    currentStockShare = 0;
  }

  private AccountSummary(AccountSummary other) {
    this.initialCashBalance = other.initialCashBalance;
    this.currentCashBalance = other.currentCashBalance;
    this.currentStockBalance = other.currentStockBalance;
    this.currentStockShare = other.currentStockShare;
  }

  public float getCurrentCashBalance() {
    return currentCashBalance;
  }

  public float getCurrentStockBalance() {
    return currentStockBalance;
  }

  public float getCurrentTotalBalance() {
    return currentCashBalance + currentStockBalance;
  }

  public boolean trade(AccountAction action, TimeSeriesAnalyzable analyzable) {
    int numShares;
    if (action.getAmountType() == AmountType.DOLLAR) {
      numShares = (int) (action.getAmount() / analyzable.getCurrentPrice());
    } else {
      numShares = (int) action.getAmount();
    }
    if (action.getActionType() == ActionType.BUY) {
      return buyStock(numShares, analyzable);
    } else {
      return sellStock(numShares, analyzable);
    }
  }

  private boolean buyStock(int shares, TimeSeriesAnalyzable analyzable) {
    float cashNeeded = shares * analyzable.getCurrentPrice();
    if (cashNeeded > currentCashBalance) {
      updateProfile(analyzable);
      return false;
    }
    currentCashBalance -= cashNeeded;
    currentStockShare += shares;
    updateProfile(analyzable);
    return true;
  }

  private boolean sellStock(int shares, TimeSeriesAnalyzable analyzable) {
    if (shares > currentStockShare) {
      updateProfile(analyzable);
      return false;
    }
    currentCashBalance += shares * analyzable.getCurrentPrice();
    currentStockShare -= shares;
    updateProfile(analyzable);
    return true;
  }

  public void updateProfile(TimeSeriesAnalyzable analyzable) {
    float currentPrice = analyzable.getCurrentPrice();
    currentStockBalance = currentStockShare * currentPrice;
  }

  public AccountSummary snapshot() {
    return new AccountSummary(this);
  }
}
