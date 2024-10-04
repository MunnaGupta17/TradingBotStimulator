# Trading Bot Application

## Overview
This application implements a simple trading bot that buys stocks when their price drops by 2% and sells them when their price rises by 3%. It averages as well if price continues to fall. The stock prices are updated randomly every 5 seconds. The application provides a RESTful API to interact with the trading bot, enabling users to check the current status, including positions, balance, and profit/loss.

## Trading Logic
1. **Initialization**:
   - The bot starts with a predefined balance and initializes stock prices for selected stocks (e.g., AAPL, GOOGL, AMZN).
  
2. **Buying Stocks**:
   - The bot checks if the current price of any stock has dropped by 2% compared to its opening price or Avg Price(in case if we hold share). 
   - If the price is 2% down, it buys the stock and updates the balance accordingly.

3. **Selling Stocks**:
   - The bot checks each owned stock's current price against its purchase price or Avg Price.
   - If the price is up by 3% from the purchase price or Avg Price, the bot sells the stock and updates the balance.

4. **Stock Price Updates**:
   - Stock prices are updated randomly every 5 seconds to simulate real-time market conditions.

## API Usage

### 1. Get Trading Bot Status
**Endpoint**: `GET /api/trading-bot/status`

**Description**: This endpoint retrieves the current status of the trading bot, including the following:
- **positions**: A map of the stocks currently held by the bot and their quantities.
- **balance**: The current balance available in the trading account.
- **profitLoss**: The total profit or loss based on the current stock prices.

**Response Example**:
```json
{
    "positions": {
        "AAPL": 10,
        "GOOGL": 5
    },
    "balance": 1200.50,
    "profitLoss": 150.00
}
```
### 2. Get Stocks Current Prices
**Endpoint**: `GET /api/stock-prices`

**Description**: this endpoint retrieves the current prices of shares.

**Response Example**:
```json
[
  {
    "symbol": "AAPL",
    "price": 161.070802474983
  },
  {
    "symbol": "GOOGL",
    "price": 2806.53211808855
  },
  {
    "symbol": "AMZN",
    "price": 3493.90162553976
  }
]
```

## Running the Application
### Prerequisites
1. **Java Development Kit (JDK) 11 or higher**
2. **Maven**

### Steps to Run
1. **Clone the Repository:** Clone the repository containing the application code
   ```bash
   git clone <repository-url>
   cd <repository-directory>
   ```
2. **Build the Application:** use Maven,run:
   ```bash
   mvn clean install
   ```
3. **Run the Application:** You can run the application using:
   ```bash
   mvn spring-boot:run
   ```
4. **Access the API:** After the application starts, you can access the trading bot status API at:
   ```bash
   http://localhost:8080/api/trading-bot/status
   http://localhost:8080/api/stock-prices
   ```

