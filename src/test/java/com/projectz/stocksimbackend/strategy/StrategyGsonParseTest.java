package com.projectz.stocksimbackend.strategy;

import com.google.gson.Gson;
import com.projectz.stocksimbackend.common.proto.strategy.Strategy;
import com.projectz.stocksimbackend.common.proto.strategy.StrategyInput;
import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StrategyGsonParseTest {
  private static final String TEST_DIR =
    "src/test/java/com/projectz/stocksimbackend/strategy/";

  private static StrategyInput readGsonData() {
    try {
      File file = new File(TEST_DIR + "SampleStrategyInput.json");
      InputStreamReader inputReader = new FileReader(file);
      Gson gson = new Gson();
      return gson.fromJson(inputReader, StrategyInput.class);
    } catch (IOException ex) {
      throw new IllegalStateException(ex);
    }
  }

  @Test
  public void test() {
    StrategyInput strategyInput = readGsonData();
    Strategy strategy = strategyInput.toStrategy();
    int x = 10;
  }
}
