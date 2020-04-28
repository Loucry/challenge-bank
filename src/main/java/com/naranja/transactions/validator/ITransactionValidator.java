package com.naranja.transactions.validator;

import com.naranja.transactions.exceptions.CustomValidationException;
import com.naranja.transactions.models.Transaction;

public interface ITransactionValidator {

    void validateCreateTransaction(Transaction transaction) throws CustomValidationException;
}
