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

    public Account create(Account acc) {
        return repo.save(acc);
    }

    public Account get(String accountNumber) {
        return repo.findById(accountNumber)
                .orElseThrow(() -> new RuntimeException("Not found"));
    }

    public Account update(String accountNumber, Account acc) {
        Account existing = get(accountNumber);

        existing.setAccountHolderName(acc.getAccountHolderName());
        existing.setCurrency(acc.getCurrency());
        existing.setBranch(acc.getBranch());

        return repo.save(existing);
    }

    public void delete(String accountNumber) {
        repo.deleteById(accountNumber);
    }

    public List<Account> getAll() {
        return repo.findAll();
    }
}