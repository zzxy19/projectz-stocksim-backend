package com.projectz.stocksimbackend.searchsymbol;

public interface SearchSymbolService {
  SearchSymbolResponse handleSearchSymbolRequest(String searchString, int maxResult);
}
