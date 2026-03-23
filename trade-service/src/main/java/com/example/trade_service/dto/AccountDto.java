package com.example.trade_service.dto;

import lombok.Data;

@Data
public class AccountDto {
    private String accountNumber;
    private String accountHolderName;
    private String currency;
    private String branch;
}
