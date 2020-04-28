package com.naranja.transactions.enums;

public enum ValidationViolationType {

    AccountAlreadyCreated("account-already-created"), InsufficientAmount("insufficient-amount"), CardNotActive("card-not-active"),
    HighFrecuency("high-frequency"), DoubledTransaction("doubled-transaction"), AllowedAmountExceeds("allowed-amount-exceeds");

    private String value;

    public String getValue() {
        return value;
    }

    ValidationViolationType(String value) {
        this.value = value;
    }
}
