package com.example.trade_service.service;

import com.example.trade_service.dto.TradeDto;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.stereotype.Service;

@Service
public class XmlParserService {

    private final XmlMapper xmlMapper = new XmlMapper();

    public TradeDto parse(String xml) {
        try {
            return xmlMapper.readValue(xml, TradeDto.class);
        } catch (Exception e) {
            throw new RuntimeException("Invalid XML", e);
        }
    }
}