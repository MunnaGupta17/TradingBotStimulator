package com.tradingbot.controller;

import com.tradingbot.model.Stock;
import com.tradingbot.service.StockPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StockPriceController {

    @Autowired
    private StockPriceService stockPriceService;

    @GetMapping("/stock-prices")
    public ResponseEntity<List<Stock>> getStockPrices() {
        List<Stock> stockPrices = stockPriceService.getStockPrices();
        return ResponseEntity.ok(stockPrices);
    }
}

