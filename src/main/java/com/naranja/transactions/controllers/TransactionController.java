package com.naranja.transactions.controllers;

import com.naranja.transactions.dto.AccountDTO;
import com.naranja.transactions.exceptions.CustomValidationException;
import com.naranja.transactions.models.Transaction;
import com.naranja.transactions.services.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Set;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    private final ITransactionService transactionService;

    @Autowired
    public TransactionController(ITransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    private AccountDTO createTransaction(@RequestBody Transaction transaction) throws CustomValidationException {
        return transactionService.createTransaction(transaction);
    }

    @PostMapping(path = "/batch", consumes = "application/json", produces = "application/json")
    private AccountDTO createTransactions(@RequestBody Transaction[] transactions) throws CustomValidationException {
        Arrays.sort(transactions, Comparator.comparing(Transaction::getTransactionTime));
        return transactionService.createTransactions(Arrays.asList(transactions));
    }

    @GetMapping(path = "/account/{accountId}", produces = "application/json")
    private Set<Transaction> getTransactionsByAccountId(@PathVariable Long accountId) {
        return transactionService.getTransactionsByAccountId(accountId);
    }

    @GetMapping(path = "/{transactionId}", produces = "application/json")
    private Transaction getTransactionById(@PathVariable Long transactionId) {
        return transactionService.getTransactionById(transactionId);
    }
}
