package com.naranja.transactions.validator.rules.transaction;

import com.naranja.transactions.enums.ValidationViolationType;
import com.naranja.transactions.models.BaseModel;
import com.naranja.transactions.models.Transaction;

public class TransactionAllowedAmountRule extends BaseTransactionRule {

    private final Long MAX_DEPOSIT_AMOUNT = 10000L;

    private final Long MAX_EXTRACTION_AMOUNT = 5000L;

    @Override
    public ValidationViolationType validate(BaseModel model) {
        Transaction transaction = (Transaction)model;

        switch (transaction.getTransactionType()) {
            case withdraw:
                if (transaction.getAmount() > MAX_EXTRACTION_AMOUNT) {
                    return ValidationViolationType.AllowedAmountExceeds;
                }
                break;
            case deposit:
                if (transaction.getAmount() > MAX_DEPOSIT_AMOUNT) {
                    return ValidationViolationType.AllowedAmountExceeds;
                }
                break;
            case purchase:
                break;
        }

        return null;
    }
}
