package com.tradingbot.service;

import com.tradingbot.model.StockPrice;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class StockPriceService {
    private List<StockPrice> stockPrices = new ArrayList<>();
    private Random random = new Random();

    public StockPriceService() {
        stockPrices.add(new StockPrice("AAPL", 150.0));
        stockPrices.add(new StockPrice("GOOGL", 2800.0));
        stockPrices.add(new StockPrice("AMZN", 3500.0));
    }

    public List<StockPrice> getStockPrices() {
        return stockPrices;
    }

    @Scheduled(fixedRate = 5000)
    public void updateStockPrices() {
        for (StockPrice stockPrice : stockPrices) {
            double change = (random.nextDouble() - 0.5) * 10; // Random change between -5 and +5
            stockPrice.setPrice(stockPrice.getPrice() + change);
        }
    }
}
