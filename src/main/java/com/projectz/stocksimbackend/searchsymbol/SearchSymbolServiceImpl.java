package com.projectz.stocksimbackend.searchsymbol;

import com.projectz.stocksimbackend.common.db.Company;
import com.projectz.stocksimbackend.common.db.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchSymbolServiceImpl implements SearchSymbolService {
  @Autowired
  public CompanyRepository companyRepository;

  @Override
  public SearchSymbolResponse handleSearchSymbolRequest(String searchString) {
    SearchSymbolResponse response = new SearchSymbolResponse();
    response.setCompanies(fetchCompanyName(searchString));
    return response;
  }

  private List<Company> fetchCompanyName(String searchString) {
    return companyRepository.searchCompanyBySymbolOrname(searchString);
  }

}
