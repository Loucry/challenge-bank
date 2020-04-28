package com.naranja.transactions.validator;

import com.naranja.transactions.enums.ValidationViolationType;
import com.naranja.transactions.exceptions.CustomValidationException;
import com.naranja.transactions.models.Transaction;
import com.naranja.transactions.services.IAccountService;
import com.naranja.transactions.services.ITransactionService;
import com.naranja.transactions.validator.rules.transaction.BaseTransactionRule;
import com.naranja.transactions.validator.rules.transaction.TransactionCardNotActiveRule;
import com.naranja.transactions.validator.rules.transaction.TransactionDoubledRule;
import com.naranja.transactions.validator.rules.transaction.TransactionHighFrecuencyRule;
import com.naranja.transactions.validator.rules.transaction.TransactionInsufficientAmmountRule;
import com.naranja.transactions.validator.rules.transaction.TransactionAllowedAmountRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class TransactionValidator implements ITransactionValidator {

    private final List<BaseTransactionRule> createTransactionRules;

    @Autowired
    public TransactionValidator(@Lazy IAccountService accountService, @Lazy ITransactionService transactionService) {
        createTransactionRules = Arrays.asList(
                new TransactionInsufficientAmmountRule(accountService),
                new TransactionCardNotActiveRule(accountService),
                new TransactionHighFrecuencyRule(transactionService),
                new TransactionDoubledRule(transactionService),
                new TransactionAllowedAmountRule());
    }

    @Override
    public void validateCreateTransaction(Transaction transaction) throws CustomValidationException {
        baseValidate(createTransactionRules, transaction);
    }

    private void baseValidate(List<BaseTransactionRule> rules, Transaction transaction) throws CustomValidationException {
        Set<ValidationViolationType> violations = new HashSet<>();
        ValidationViolationType validationViolationType = null;

        for (BaseTransactionRule rule : rules) {
            validationViolationType = rule.validate(transaction);
            if (validationViolationType != null) {
                violations.add(validationViolationType);
            }
        }

        if (!violations.isEmpty()) {
            CustomValidationException ex = new CustomValidationException();
            ex.getViolations().addAll(violations);
            throw ex;
        }
    }
}
