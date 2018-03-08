package com.projectz.stocksimbackend;

import java.util.concurrent.atomic.AtomicLong;

import com.projectz.stocksimbackend.searchsymbol.SearchSymbolResponse;
import com.projectz.stocksimbackend.searchsymbol.SearchSymbolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DispatcherController {

  private final AtomicLong counter = new AtomicLong();

  @Autowired
  SearchSymbolService searchSymbolService;

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
    @RequestParam(name = "str", required=false, defaultValue = "") String searchString) {
    return searchSymbolService.handleSearchSymbolRequest(searchString);
  }

}