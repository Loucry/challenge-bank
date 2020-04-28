package com.naranja.transactions.exceptions;

import com.naranja.transactions.enums.ValidationViolationType;

import java.util.HashSet;
import java.util.Set;

public class CustomValidationException extends Exception {

    private Set<ValidationViolationType> violations = new HashSet<>();

    public Set<ValidationViolationType> getViolations() {
        return violations;
    }

    public void setViolations(Set<ValidationViolationType> violations) {
        this.violations = violations;
    }
}
