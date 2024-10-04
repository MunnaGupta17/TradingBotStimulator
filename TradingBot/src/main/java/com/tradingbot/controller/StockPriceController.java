package com.tradingbot.controller;

import com.tradingbot.model.StockPrice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StockPriceController {

    @GetMapping("/stock-prices")
    public ResponseEntity<List<StockPrice>> getStockPrices() {
        List<StockPrice> stockPrices = Arrays.asList(
                new StockPrice("AAPL", 150.0),
                new StockPrice("GOOGL", 2800.0),
                new StockPrice("AMZN", 3500.0)
        );


        return ResponseEntity.ok(stockPrices);
    }
}
