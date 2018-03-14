package com.projectz.stocksimbackend.common.db;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( name = "company_dev" )
public class Company {
  @Id
  private String symbol;
  private String name;
  private Integer ipoyear;
  private String sector;
  private String industry;

  public String getSymbol() {
    return symbol;
  }

  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }
}
