package com.tradingbot.model;

public class Stock {
    private String symbol;
    private double price;
    private double openingPrice;
    private double avgStockPrice;
    
    public double getAvgStockPrice() {
		return avgStockPrice;
	}

	public void setAvgStockPrice(double avgStockPrice) {
		this.avgStockPrice = avgStockPrice;
	}

	public Stock(String symbol, double price, double openingPrice) {
		super();
		this.symbol = symbol;
		this.price = price;
		this.openingPrice = openingPrice;
	}

	public double getOpeningPrice() {
		return openingPrice;
	}

	public void setOpeningPrice(double openingPrice) {
		this.openingPrice = openingPrice;
	}

	public Stock() {
    }

    public Stock(String symbol, double price) {
        this.symbol = symbol;
        this.price = price;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "StockPrice{" +
                "symbol='" + symbol + '\'' +
                ", price=" + price +
                '}';
    }
}
