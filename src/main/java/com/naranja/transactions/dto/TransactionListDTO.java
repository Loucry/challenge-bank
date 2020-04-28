package com.naranja.transactions.dto;

import com.naranja.transactions.models.Transaction;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TransactionListDTO implements Serializable {

    private List<Transaction> transactions = new ArrayList<>();

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
