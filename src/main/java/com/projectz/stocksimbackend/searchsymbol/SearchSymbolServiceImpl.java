package com.projectz.stocksimbackend.searchsymbol;

import com.projectz.stocksimbackend.common.db.Company;
import com.projectz.stocksimbackend.common.db.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
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
    HashSet<Company> companiesOfInterest = new HashSet<>();
    String queryArgument = "%" + searchString + "%";
    companiesOfInterest.addAll(companyRepository.searchCompanyByName(queryArgument));
    companiesOfInterest.addAll(companyRepository.searchCompanyBySymbol(queryArgument));
    return new ArrayList<>(companiesOfInterest);
  }

}
