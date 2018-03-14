package com.projectz.stocksimbackend.searchsymbol;

import com.projectz.stocksimbackend.common.db.Company;

import java.util.List;

public class SearchSymbolResponse {
  private List<Company> companies;

  public List<Company> getCompanies() {
    return companies;
  }

  public void setCompanies(List<Company> companies) {
    this.companies = companies;
  }
}
