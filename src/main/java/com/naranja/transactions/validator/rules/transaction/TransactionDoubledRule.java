package com.naranja.transactions.validator.rules.transaction;

import com.naranja.transactions.enums.ValidationViolationType;
import com.naranja.transactions.models.BaseModel;
import com.naranja.transactions.models.Transaction;
import com.naranja.transactions.services.ITransactionService;

public class TransactionDoubledRule extends BaseTransactionRule {

    private final ITransactionService transactionService;
    private final Long DOUBLED_TRANSACTION_THRESHOLD = 2L;

    public TransactionDoubledRule(ITransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @Override
    public ValidationViolationType validate(BaseModel model) {
        Transaction transaction = (Transaction)model;

        if (transactionService.countDoubledTransaction(transaction) >= 2) {
            return ValidationViolationType.DoubledTransaction;
        } else {
            return null;
        }
    }
}
