package com.example.trade_service.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.example.trade_service.service.TradeService;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class TradeConsumer {

    private final TradeService tradeService;
    private final ObjectMapper mapper = new ObjectMapper();

    @KafkaListener(topics = "test-topic", groupId = "trade-group")
    public void consume(String xml) {

        log.info("Received XML: {}", xml);

        try {
            var result = tradeService.process(xml);

            log.info("Enriched Trade: {}", mapper.writeValueAsString(result));

        } catch (Exception e) {
            log.error("Error processing message: {}", e.getMessage(), e);
        }
    }
}