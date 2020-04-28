package com.naranja.transactions.controllers;

import com.naranja.transactions.exceptions.CustomValidationException;
import com.naranja.transactions.models.Account;
import com.naranja.transactions.services.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {

    private final IAccountService accountService;

    @Autowired
    public AccountController(IAccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    private Account createAccount(@RequestBody Account account) throws CustomValidationException {
        return accountService.createAccount(account);
    }

    @PutMapping(consumes = "application/json", produces = "application/json")
    private Account updateAccount(@RequestBody Account account) throws CustomValidationException {
        return accountService.updateAccount(account);
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    private Account getAccount(@PathVariable Long id) {
        return accountService.getAccount(id);
    }
}
