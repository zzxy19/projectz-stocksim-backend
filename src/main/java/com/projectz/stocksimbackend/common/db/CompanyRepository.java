package com.projectz.stocksimbackend.common.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, String> {
  static final String SEARCH_COMPANY_SYMBOL_QUERY =
      "select c from Company c where c.symbol like ?1 ";

  static final String SEARCH_COMPANY_NAME_QUERY =
      "select c from Company c where c.name like ?1 ";

  @Query(SEARCH_COMPANY_SYMBOL_QUERY)
  List<Company> searchCompanyBySymbol(String symbol);

  @Query(SEARCH_COMPANY_NAME_QUERY)
  List<Company> searchCompanyByName(String name);
}
