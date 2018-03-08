package com.projectz.stocksimbackend.searchsymbol;

import org.springframework.stereotype.Service;

@Service
public class SearchSymbolServiceImpl implements SearchSymbolService {
  @Override
  public SearchSymbolResponse handleSearchSymbolRequest(String searchString) {
    SearchSymbolResponse response = new SearchSymbolResponse();
    response.setCanonicalName("Micron");
    response.setSymbol("MU");
    return response;
  }
}
