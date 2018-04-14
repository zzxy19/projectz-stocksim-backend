package com.projectz.stocksimbackend.common.proto.strategy;

import java.util.ArrayList;
import java.util.List;

public final class TimeSeriesActionable {
  private final String symbol;
  private final AccountSummary initialAccountSummary;

  private AccountSummary latestAccountSummary;
  private List<Boolean> actionHistory;
  private List<AccountSummary> accountSummaryHistory;

  public TimeSeriesActionable(String symbol, AccountSummary initialAccount) {
    this.symbol = symbol;
    this.initialAccountSummary = initialAccount;
    this.latestAccountSummary = initialAccount;
    actionHistory = new ArrayList<>();
    accountSummaryHistory = new ArrayList<>();
  }

  public void noAction(TimeSeriesAnalyzable analyzable) {
    latestAccountSummary.updateProfile(analyzable);
    actionHistory.add(false);
    accountSummaryHistory.add(latestAccountSummary.snapshot());
  }

  public boolean executeAction(TimeSeriesAnalyzable analyzable, AccountAction action) {
    if (!latestAccountSummary.trade(action, analyzable)) {
      actionHistory.add(false);
      accountSummaryHistory.add(latestAccountSummary.snapshot());
      return false;
    }
    actionHistory.add(true);
    accountSummaryHistory.add(latestAccountSummary.snapshot());
    return true;
  }
}
