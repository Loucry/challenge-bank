package com.naranja.transactions.dto;

import com.naranja.transactions.enums.TransactionType;
import com.naranja.transactions.models.Transaction;

import java.time.LocalDateTime;

public class TransactionDTO extends BaseModelDTO {

    private TransactionType transactionType;

    private String commerce;

    private Long amount;

    private LocalDateTime transactionTime;

    private Long accountId;

    public TransactionDTO(Transaction transaction) {
        super(transaction);
        this.transactionType = transaction.getTransactionType();
        this.commerce = transaction.getCommerce();
        this.amount = transaction.getAmount();
        this.transactionTime = transaction.getTransactionTime();
        this.accountId = transaction.getAccountId();
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public String getCommerce() {
        return commerce;
    }

    public void setCommerce(String commerce) {
        this.commerce = commerce;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public LocalDateTime getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(LocalDateTime transactionTime) {
        this.transactionTime = transactionTime;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }
}
