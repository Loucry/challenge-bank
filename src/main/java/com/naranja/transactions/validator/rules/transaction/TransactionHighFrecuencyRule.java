package com.naranja.transactions.validator.rules.transaction;

import com.naranja.transactions.enums.ValidationViolationType;
import com.naranja.transactions.models.BaseModel;
import com.naranja.transactions.models.Transaction;
import com.naranja.transactions.services.ITransactionService;

public class TransactionHighFrecuencyRule extends BaseTransactionRule {

    private final ITransactionService transactionService;
    private final Long SECONDS_INTERVAL = 120L;
    private final Long MAX_TRANSACTIONS = 10L;

    public TransactionHighFrecuencyRule(ITransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @Override
    public ValidationViolationType validate(BaseModel model) {
        Transaction transaction = (Transaction)model;

        if (transactionService.highFrecuency(transaction.getTransactionTime(), SECONDS_INTERVAL) >= MAX_TRANSACTIONS) {
            return ValidationViolationType.HighFrecuency;
        } else {
            return null;
        }
    }
}
