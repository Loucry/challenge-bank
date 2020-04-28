package com.naranja.transactions.validator.rules.account;

import com.naranja.transactions.enums.ValidationViolationType;
import com.naranja.transactions.models.Account;
import com.naranja.transactions.models.BaseModel;
import com.naranja.transactions.services.IAccountService;

public class AccountRepeatedNameOrDniRule extends BaseAccountRule {

    private final IAccountService accountService;

    public AccountRepeatedNameOrDniRule(IAccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public ValidationViolationType validate(BaseModel model) {
        Account account = (Account)model;

        if (accountService.checkRepeatedNameAndDNI(account)) {
            return ValidationViolationType.AccountAlreadyCreated;
        } else {
            return null;
        }
    }
}
