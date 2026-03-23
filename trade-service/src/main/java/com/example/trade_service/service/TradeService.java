package com.example.trade_service.service;

import com.example.trade_service.client.AccountClient;
import com.example.trade_service.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TradeService {

    private final XmlParserService parser;
    private final AccountClient accountClient;

    public EnrichedTradeDto process(String xml) {

        log.info("Parsing XML...");

        TradeDto trade = parser.parse(xml);

        log.info("Calling Account Service for account: {}", trade.getAccountNumber());

        AccountDto account = accountClient.getAccount(trade.getAccountNumber());

        log.info("Account fetched successfully: {}", account.getAccountHolderName());

        EnrichedTradeDto enriched = new EnrichedTradeDto();

        enriched.setTradeId(trade.getTradeId());
        enriched.setTime(trade.getTime());
        enriched.setAmount(trade.getAmount());
        enriched.setAccountNumber(trade.getAccountNumber());

        enriched.setAccountHolderName(account.getAccountHolderName());
        enriched.setCurrency(account.getCurrency());
        enriched.setBranch(account.getBranch());

        return enriched;
    }
}