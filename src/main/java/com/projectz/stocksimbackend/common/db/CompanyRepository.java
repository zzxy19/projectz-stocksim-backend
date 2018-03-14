package com.projectz.stocksimbackend.common.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, String> {
  static final String SEARCH_COMPANY_QUERY =
    "select * from company_prod c where c.symbol like ?1 "
        + "union distinct "
        + "select * from company_prod c where c.name like ?1 ";

  @Query(SEARCH_COMPANY_QUERY)
  List<Company> searchCompanyBySymbolOrname(String symbol);
}
