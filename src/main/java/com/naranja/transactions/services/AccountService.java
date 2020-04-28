package com.naranja.transactions.services;

import com.naranja.transactions.exceptions.CustomValidationException;
import com.naranja.transactions.models.Account;
import com.naranja.transactions.repositories.AccountRepository;
import com.naranja.transactions.validator.AccountValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService implements IAccountService {

    private final AccountRepository accountRepository;

    private final AccountValidator accountValidator;

    @Autowired
    public AccountService(AccountRepository accountRepository, AccountValidator accountValidator) {
        this.accountRepository = accountRepository;
        this.accountValidator = accountValidator;
    }

    @Override
    public Account createAccount(Account account) throws CustomValidationException {
        accountValidator.validateCreateAccount(account);
        return accountRepository.save(account);
    }

    @Override
    public Boolean checkRepeatedNameAndDNI(Account account) {
        return accountRepository.countByNameAndDni(account.getName(), account.getDni()) > 0;
    }

    @Override
    public Account getAccount(Long accountId) {
        return accountRepository.findById(accountId).orElseThrow(() -> new RuntimeException("Account with id " + accountId + " not found"));
    }

    @Override
    public Account updateAmount(Account account) {
        Account dbAccount = getAccount(account.getId());

        dbAccount.setAvailableAmount(account.getAvailableAmount());

        return accountRepository.save(dbAccount);
    }

    @Override
    public Account updateAccount(Account account) throws CustomValidationException {
        Account dbAccount = getAccount(account.getId());

        dbAccount.setActiveCard(account.getActiveCard());
        Boolean updatedNameOrDni = false;
        if (!account.getName().equals(dbAccount.getName())) {
            dbAccount.setName(account.getName());
            updatedNameOrDni = true;
        }
        if (!account.getDni().equals(dbAccount.getDni())) {
            dbAccount.setName(account.getName());
            updatedNameOrDni = true;
        }

        if (updatedNameOrDni) {
            accountValidator.validateCreateAccount(dbAccount);
        }

        return accountRepository.save(dbAccount);
    }
}
