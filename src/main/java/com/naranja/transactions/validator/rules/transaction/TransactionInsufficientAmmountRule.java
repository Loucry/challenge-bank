package com.naranja.transactions.validator.rules.transaction;

import com.naranja.transactions.enums.ValidationViolationType;
import com.naranja.transactions.models.Account;
import com.naranja.transactions.models.BaseModel;
import com.naranja.transactions.models.Transaction;
import com.naranja.transactions.services.IAccountService;

public class TransactionInsufficientAmmountRule extends BaseTransactionRule {

    private final IAccountService accountService;

    public TransactionInsufficientAmmountRule(IAccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public ValidationViolationType validate(BaseModel model) {
        Transaction transaction = (Transaction) model;

        Account account = accountService.getAccount(transaction.getAccount().getId());

        switch (transaction.getTransactionType()) {
            case withdraw:
            case purchase:
                if (transaction.getAmount() > account.getAvailableAmount()) {
                    return ValidationViolationType.InsufficientAmount;
                }
                break;
            case deposit:
                break;
        }

        return null;
    }
}
