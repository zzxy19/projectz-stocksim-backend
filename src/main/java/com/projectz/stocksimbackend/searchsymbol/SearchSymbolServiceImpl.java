package com.projectz.stocksimbackend.searchsymbol;

import com.projectz.stocksimbackend.common.db.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchSymbolServiceImpl implements SearchSymbolService {
  @Autowired
  public CompanyRepository companyRepository;

  @Override
  public SearchSymbolResponse handleSearchSymbolRequest(String searchString) {
    SearchSymbolResponse response = new SearchSymbolResponse();
    response.setData(fetchCompanyName(searchString));
    response.setSymbol(searchString);
    return response;
  }

  private String fetchCompanyName(String searchString) {
    return companyRepository.findById(searchString).get().getName();
  }

}
