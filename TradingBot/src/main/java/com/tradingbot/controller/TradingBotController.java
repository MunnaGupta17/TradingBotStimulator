package com.tradingbot.controller;

import com.tradingbot.service.TradingBotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class TradingBotController {

    @Autowired
    private TradingBotService tradingBotService;

    /**
     * Retrieves the current status of the trading bot.
     * 
     * This method responds to GET requests at the "/trading-bot/status" endpoint, returning a
     * JSON object containing the current positions held, the available balance, and the overall
     * profit or loss of the trading bot. The response is encapsulated in a ResponseEntity with
     * an HTTP 200 OK status.
     * 
     * @return ResponseEntity containing a map with the trading bot's positions, balance, and profit/loss.
     */
    @GetMapping("/trading-bot/status")
    public ResponseEntity<Map<String, Object>> getTradingBotStatus() {
        Map<String, Object> status = Map.of(
                "positions", tradingBotService.getPositions(),
                "balance", tradingBotService.getBalance(),
                "profitLoss", tradingBotService.getProfitLoss()
        );
        return ResponseEntity.ok(status);
    }
}
