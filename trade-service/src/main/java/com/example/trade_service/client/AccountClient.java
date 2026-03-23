package com.example.trade_service.client;

import com.example.trade_service.dto.AccountDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AccountClient {

    private final RestTemplate restTemplate = new RestTemplate();

    public AccountDto getAccount(String accountNumber) {

        String url = "http://localhost:8080/accounts/" + accountNumber;

        return restTemplate.getForObject(url, AccountDto.class);
    }
}
