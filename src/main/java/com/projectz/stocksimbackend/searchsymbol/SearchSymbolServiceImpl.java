package com.projectz.stocksimbackend.searchsymbol;

import com.projectz.stocksimbackend.common.db.Company;
import com.projectz.stocksimbackend.common.db.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

@Service
public class SearchSymbolServiceImpl implements SearchSymbolService {
  @Autowired
  public CompanyRepository companyRepository;

  @Override
  public SearchSymbolResponse handleSearchSymbolRequest(String searchString, int maxResult) {
    SearchSymbolResponse response = new SearchSymbolResponse();
    response.setCompanies(fetchCompanyName(searchString, maxResult));
    return response;
  }

  private List<Company> fetchCompanyName(String searchString, int maxResult) {
    LinkedHashSet<Company> companiesOfInterest = new LinkedHashSet<>();
    String symbolQueryArgument = searchString + "%";
    String companyQueryArgument = "%" + searchString + "%";
    companiesOfInterest.addAll(companyRepository.searchCompanyByName(symbolQueryArgument));
    companiesOfInterest.addAll(companyRepository.searchCompanyBySymbol(companyQueryArgument));
    int n = companiesOfInterest.size();
    int outputCount = n > maxResult ? maxResult : n;
    return new ArrayList<>(companiesOfInterest).subList(0, outputCount);
  }
}
