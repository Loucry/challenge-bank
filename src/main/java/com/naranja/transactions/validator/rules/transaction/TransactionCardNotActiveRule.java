package com.naranja.transactions.validator.rules.transaction;

import com.naranja.transactions.enums.ValidationViolationType;
import com.naranja.transactions.models.Account;
import com.naranja.transactions.models.BaseModel;
import com.naranja.transactions.models.Transaction;
import com.naranja.transactions.services.IAccountService;

public class TransactionCardNotActiveRule extends BaseTransactionRule {

    private final IAccountService accountService;

    public TransactionCardNotActiveRule(IAccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public ValidationViolationType validate(BaseModel model) {
        Transaction transaction = (Transaction) model;


        Account account = accountService.getAccount(transaction.getAccount().getId());

        if (!account.getActiveCard()) {
            return ValidationViolationType.CardNotActive;
        } else {
            return null;
        }
    }
}
