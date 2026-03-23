package com.example.trade_service.dto;

import lombok.Data;

@Data
public class EnrichedTradeDto {

    private String tradeId;
    private String time;
    private Double amount;
    private String accountNumber;

    private String accountHolderName;
    private String currency;
    private String branch;
}
