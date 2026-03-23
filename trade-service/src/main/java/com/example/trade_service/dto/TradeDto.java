package com.example.trade_service.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

@Data
@JacksonXmlRootElement(localName = "trade")
public class TradeDto {

    private String tradeId;
    private String time;
    private Double amount;
    private String accountNumber;
}