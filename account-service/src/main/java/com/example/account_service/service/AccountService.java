package com.example.account_service.service;

import com.example.account_service.model.Account;
import com.example.account_service.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository repo;

    public Account getAccount(String accountNumber) {
        return repo.findById(accountNumber)
                .orElseThrow(() -> new RuntimeException("Account not found"));
    }

    public Account create(Account acc) {
        return repo.save(acc);
    }

    public List<Account> getAll() {
        return repo.findAll();
    }
}
