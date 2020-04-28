package com.naranja.transactions.validator;

import com.naranja.transactions.enums.ValidationViolationType;
import com.naranja.transactions.exceptions.CustomValidationException;
import com.naranja.transactions.models.Account;
import com.naranja.transactions.services.IAccountService;
import com.naranja.transactions.validator.rules.account.AccountRepeatedNameOrDniRule;
import com.naranja.transactions.validator.rules.account.BaseAccountRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class AccountValidator implements IAccountValidator {

    private final List<BaseAccountRule> createAccountRules;

    @Autowired
    public AccountValidator(@Lazy IAccountService accountService) {
        createAccountRules = Collections.singletonList(new AccountRepeatedNameOrDniRule(accountService));
    }

    @Override
    public void validateCreateAccount(Account account) throws CustomValidationException {
        baseValidate(createAccountRules, account);
    }

    private void baseValidate(List<BaseAccountRule> rules, Account account) throws CustomValidationException {
        Set<ValidationViolationType> violations = new HashSet<>();
        ValidationViolationType validationViolationType = null;

        for (BaseAccountRule rule : rules) {
            validationViolationType = rule.validate(account);
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
