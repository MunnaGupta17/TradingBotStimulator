package com.tradingbot.service;

import com.tradingbot.model.StockPrice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TradingBotService {

    @Autowired
    private StockPriceService stockPriceService;

    private Map<String, Integer> positions = new HashMap<>(); // Stock symbol to number of shares
    private double balance = 100000.0; // Initial balance
    private double initialBalance = 100000.0;

    public TradingBotService() {
        positions.put("AAPL", 0);
        positions.put("GOOGL", 0);
        positions.put("AMZN", 0);
    }

    @Scheduled(fixedRate = 5000)
    public void executeTradingStrategy() {
        List<StockPrice> stockPrices = stockPriceService.getStockPrices();

        for (StockPrice stockPrice : stockPrices) {
            String symbol = stockPrice.getSymbol();
            double price = stockPrice.getPrice();
            int position = positions.getOrDefault(symbol, 0);

            // Buy condition: price drops by 2%
            if (price <= stockPrice.getPrice() * 0.98 && balance >= price) {
                positions.put(symbol, position + 1);
                balance -= price;
                System.out.println("Bought 1 share of " + symbol + " at " + price);
            }

            // Sell condition: price rises by 3%
            if (price >= stockPrice.getPrice() * 1.03 && position > 0) {
                positions.put(symbol, position - 1);
                balance += price;
                System.out.println("Sold 1 share of " + symbol + " at " + price);
            }
        }
    }

    public double getProfitLoss() {
        double currentBalance = balance;
        for (Map.Entry<String, Integer> entry : positions.entrySet()) {
            String symbol = entry.getKey();
            int position = entry.getValue();
            double currentPrice = stockPriceService.getStockPrices().stream()
                    .filter(sp -> sp.getSymbol().equals(symbol))
                    .findFirst()
                    .orElse(new StockPrice(symbol, 0.0))
                    .getPrice();
            currentBalance += position * currentPrice;
        }
        return currentBalance - initialBalance;
    }

    public Map<String, Integer> getPositions() {
        return positions;
    }

    public double getBalance() {
        return balance;
    }
}
