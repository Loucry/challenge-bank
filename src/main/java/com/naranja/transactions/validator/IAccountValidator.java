package com.naranja.transactions.validator;

import com.naranja.transactions.exceptions.CustomValidationException;
import com.naranja.transactions.models.Account;

public interface IAccountValidator {

    void validateCreateAccount(Account account) throws CustomValidationException;
}
