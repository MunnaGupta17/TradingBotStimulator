package com.tradingbot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Trade {
    private String symbol;
    private double price;
    private int quantity;
    private String type; // "buy" or "sell"
    private LocalDateTime timestamp;
}
