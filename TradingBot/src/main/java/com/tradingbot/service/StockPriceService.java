package com.tradingbot.service;

import com.tradingbot.model.Stock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class StockPriceService {
    private List<Stock> stocks = new ArrayList<>();
    private Random random = new Random();

    /**
     * Constructor that initializes the stock prices for various stocks for mocking.
     * In this constructor, stocks are added to the stocks list each with Symbol, Price & opening price.
     */
    public StockPriceService() {
        stocks.add(new Stock("AAPL", 100.0,100.0));
        stocks.add(new Stock("GOOGL", 170.0,170.0));
        stocks.add(new Stock("AMZN", 210.0,210.0));
    }

    public List<Stock> getStockPrices() {
        return stocks;
    }
    
    /**
     * Updates the stock prices at a fixed rate.
     * 
     * This scheduled method runs every 5000 milliseconds (5 seconds), applying a random fluctuation 
     * to each stock price in the list. The change is calculated as a random value between -5 and +5,
     * simulating real-world market variations.
     */
    @Scheduled(fixedRate = 5000)
    public void updateStockPrices() {
        for (Stock stockPrice : stocks) {
            double change = (random.nextDouble() - 0.5) * 10; // Random change between -5 and +5
            stockPrice.setPrice(stockPrice.getPrice() + change);
        }
    }
}
