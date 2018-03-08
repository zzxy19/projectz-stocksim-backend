package com.projectz.stocksimbackend.searchsymbol;

import com.projectz.stocksimbackend.common.stockapi.AlphaVantageAPIService;
import org.springframework.stereotype.Service;

@Service
public class SearchSymbolServiceImpl implements SearchSymbolService {
  @Override
  public SearchSymbolResponse handleSearchSymbolRequest(String searchString) {
    SearchSymbolResponse response = new SearchSymbolResponse();
    response.setData(fetchStockAPIResponse(searchString));
    response.setSymbol(searchString);
    return response;
  }

  public String fetchStockAPIResponse(String symbol) {
    return AlphaVantageAPIService.fetchPastDayTimeSeriesData(symbol);
  }

}
