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
    String fuzzySearchString = "%" + searchString + "%";
    List<Company> fuzzySymbolMatches = companyRepository.searchCompanyBySymbol(fuzzySearchString);
    for (Company potentialCompany : fuzzySymbolMatches) {
      if (potentialCompany.getSymbol().equals(searchString)) {
        int index = fuzzySymbolMatches.indexOf(potentialCompany);
        Company first = fuzzySymbolMatches.get(0);
        fuzzySymbolMatches.set(0, potentialCompany);
        fuzzySymbolMatches.set(index, first);
        break;
      }
    }
    List<Company> fuzzyNameMatches = companyRepository.searchCompanyByName(fuzzySearchString);
    companiesOfInterest.addAll(fuzzySymbolMatches);
    companiesOfInterest.addAll(fuzzyNameMatches);
    int n = companiesOfInterest.size();
    int outputCount = n > maxResult ? maxResult : n;
    return new ArrayList<>(companiesOfInterest).subList(0, outputCount);
  }
}
