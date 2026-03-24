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

    // CREATE
    @PostMapping
    public Account create(@RequestBody Account acc) {
        return service.create(acc);
    }

    // READ
    @GetMapping("/{accountNumber}")
    public Account get(@PathVariable String accountNumber) {
        return service.get(accountNumber);
    }

    // UPDATE
    @PutMapping("/{accountNumber}")
    public Account update(@PathVariable String accountNumber,
                          @RequestBody Account acc) {
        return service.update(accountNumber, acc);
    }

    // DELETE
    @DeleteMapping("/{accountNumber}")
    public void delete(@PathVariable String accountNumber) {
        service.delete(accountNumber);
    }

    // GET ALL (optional but good)
    @GetMapping
    public List<Account> getAll() {
        return service.getAll();
    }
}