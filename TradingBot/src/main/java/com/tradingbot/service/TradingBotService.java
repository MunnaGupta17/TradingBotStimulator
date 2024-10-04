package com.tradingbot.service;

import com.tradingbot.model.Stock;
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

	@Scheduled(fixedRate = 40000)
	public void executeTradingStrategy() {
		List<Stock> stockPrices = stockPriceService.getStockPrices();

		for (Stock stockPrice : stockPrices) {
			String symbol = stockPrice.getSymbol();
			double price = stockPrice.getPrice();
			int avlQuantity = positions.getOrDefault(symbol, 0);

			System.out.println("symbol :" + symbol + " opening : " + stockPrice.getOpeningPrice() + "currentprice : "
					+ price + " Position : " + positions.size());

			/*
			 * buyingComparePrice is going to be used for if we are buying share for the
			 * first time then the value of it will be the opening price of the stock;
			 * otherwise, it will be set to AvrageStockPrice.
			 */
			double buyingComparePrice = stockPrice.getOpeningPrice();
			if (positions.containsKey(symbol)) {
				// here since we have the stock in our portfolio thus we are setting it to
				// AvgPrice of the Stock.
				buyingComparePrice = stockPrice.getAvgStockPrice();
			}
			// Buy condition: price drops by 2%
			if (price <= buyingComparePrice * 0.98 && balance >= price) {

				/*
				 * Here we are checking if we have the stock in our portfolio, then we are going
				 * to AVG. the price of our stock.
				 */
				if (positions.containsKey(symbol)) {
					int position = positions.get(symbol); // this is the number of shares we already have in our
															// portfolio of this company
					double avgStockPrice = stockPrice.getAvgStockPrice();
					avgStockPrice = ((position * avgStockPrice) + price) / (position + 1); // here we are averaging the
																							// price
					stockPrice.setAvgStockPrice(avgStockPrice); // here we set the new Stock Average price.
					positions.put(symbol, position + 1); // here we increase the no of share we have (as we bought one
															// now).
					balance -= price; // debit money from balance as we are buying share.
				} else {
					/*
					 * this else condition is for if we are buying share for the first time.
					 */
					stockPrice.setAvgStockPrice(price); // avg price setting.
					positions.put(symbol, 1); // updated the stock in position.
					balance -= price; // debit money from balance as we are buying share.
				}
				System.out.println("Bought 1 share of " + symbol + " at " + price);
			}

			// Sell condition: price rises by 3%
			/*
			 * here we are comparing the current price with the avg stock price. and after
			 * selling the stocks we are going remove the share from the positions and going
			 * to set avg Stock price to 0.
			 */
			if (price >= stockPrice.getAvgStockPrice() * 1.03 && avlQuantity > 0) {
				int quantity = positions.get(symbol);
				positions.remove(symbol);
				balance += price * quantity;
				stockPrice.setAvgStockPrice(0);
				stockPrice.setOpeningPrice(price);
				System.out.println("Sold " + quantity + " share of " + symbol + " at " + price);
			}
		}
	}

	/**
	 * Calculates and returns the overall profit or loss of the trading bot.
	 * 
	 * This method sums the current value of all held positions and adds it to the current balance.
	 * It then subtracts the initial balance to determine the profit or loss.
	 * Positive values indicate a profit, while negative values indicate a loss.
	 * 
	 * returns The overall profit or loss amount.
	 */
	public double getProfitLoss() {
		double currentBalance = balance;
		for (Map.Entry<String, Integer> entry : positions.entrySet()) {
			String symbol = entry.getKey();
			int position = entry.getValue();
			double currentPrice = stockPriceService.getStockPrices().stream()
					.filter(sp -> sp.getSymbol().equals(symbol)).findFirst().orElse(new Stock(symbol, 0.0))
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
