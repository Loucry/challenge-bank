package com.naranja.transactions.services;

import com.naranja.transactions.exceptions.CustomValidationException;
import com.naranja.transactions.models.Account;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public interface IAccountService {

    @Transactional
    Account createAccount(Account account) throws CustomValidationException;

    @Transactional(readOnly = true)
    Boolean checkRepeatedNameAndDNI(Account account);

    @Transactional(readOnly = true)
    Account getAccount(Long accountId);

    @Transactional
    Account updateAmount(Account account);

    @Transactional
    Account updateAccount(Account account) throws CustomValidationException;
}
