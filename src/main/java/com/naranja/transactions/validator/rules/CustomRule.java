package com.naranja.transactions.validator.rules;

import com.naranja.transactions.enums.ValidationViolationType;
import com.naranja.transactions.models.BaseModel;

public interface CustomRule {

    ValidationViolationType validate(BaseModel model);
}
