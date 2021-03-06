package com.projectz.stocksimbackend.common.db;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( name = "company_prod" )
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

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getIpoyear() {
    return ipoyear;
  }

  public void setIpoyear(Integer ipoyear) {
    this.ipoyear = ipoyear;
  }

  public String getSector() {
    return sector;
  }

  public void setSector(String sector) {
    this.sector = sector;
  }

  public String getIndustry() {
    return industry;
  }

  public void setIndustry(String industry) {
    this.industry = industry;
  }
}
