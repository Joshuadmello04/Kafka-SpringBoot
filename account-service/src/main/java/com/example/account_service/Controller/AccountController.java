package com.example.account_service.Controller;

import com.example.account_service.model.Account;
import com.example.account_service.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService service;

    @GetMapping("/{accountNumber}")
    public Account getAccount(@PathVariable String accountNumber) {
        return service.getAccount(accountNumber);
    }

    @PostMapping
    public Account create(@RequestBody Account acc) {
        return service.create(acc);
    }

    @GetMapping
    public List<Account> getAll() {
        return service.getAll();
    }
}