package com.projectz.stocksimbackend;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

import com.projectz.stocksimbackend.common.Exception.BadRequestException;
import com.projectz.stocksimbackend.searchsymbol.SearchSymbolResponse;
import com.projectz.stocksimbackend.searchsymbol.SearchSymbolService;
import com.projectz.stocksimbackend.showpricetimeline.ShowPriceTimelineResponse;
import com.projectz.stocksimbackend.showpricetimeline.ShowPriceTimelineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

@Controller
public class DispatcherController {

  private final AtomicLong counter = new AtomicLong();

  @Autowired
  SearchSymbolService searchSymbolService;
  @Autowired
  ShowPriceTimelineService showPriceTimelineService;

  @GetMapping("/")
  @ResponseBody
  public String handleGetHomePage() {
    return "Try one of the implemented methods: /dummy, /searchSymbol";
  }

  @GetMapping("/dummy")
  @ResponseBody
  public String handleGetDummy(
      @RequestParam(name = "name", required = false, defaultValue = "Dummy") String name) {
    return "Hello " + name + " #" + counter.incrementAndGet();
  }

  @GetMapping("/searchSymbol")
  @ResponseBody
  public SearchSymbolResponse handleGetSearchSymbol(
      @RequestParam(name = "symbol") String searchString,
      @RequestParam(name = "max_result", required = false, defaultValue = "10") int maxResult) {
    return searchSymbolService.handleSearchSymbolRequest(searchString, maxResult);
  }

  @GetMapping("/showPriceTimeline")
  @ResponseBody
  public ShowPriceTimelineResponse handleGetShowPriceTimeline(
      @RequestParam(name = "symbol") String symbol,
      @RequestParam(name = "type", required = false, defaultValue = "day") String type,
      @RequestParam(name = "date_range", required = false, defaultValue = "1") int dateRange,
      @RequestParam(name = "start_date", required = false, defaultValue = "0") int startDate) {
    return showPriceTimelineService.handleShowPriceTimelineRequest(symbol, type, dateRange, startDate);
  }

}