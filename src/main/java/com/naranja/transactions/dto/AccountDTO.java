package com.naranja.transactions.dto;

import com.naranja.transactions.models.Account;
import com.naranja.transactions.models.Transaction;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class AccountDTO extends BaseModelDTO {

    private String name;

    private Integer dni;

    private Boolean activeCard;

    private Long availableAmount = 0L;

    private Set<TransactionDTO> transactions = new HashSet<TransactionDTO>();

    public AccountDTO(Account account) {
        super(account);
        this.name = account.getName();
        this.dni = account.getDni();
        this.activeCard = account.getActiveCard();
        this.availableAmount = account.getAvailableAmount();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDni() {
        return dni;
    }

    public void setDni(Integer dni) {
        this.dni = dni;
    }

    public Boolean getActiveCard() {
        return activeCard;
    }

    public void setActiveCard(Boolean activeCard) {
        this.activeCard = activeCard;
    }

    public Long getAvailableAmount() {
        return availableAmount;
    }

    public void setAvailableAmount(Long availableAmount) {
        this.availableAmount = availableAmount;
    }

    public Set<TransactionDTO> getTransactions() {
        return transactions;
    }

    public void setTransactions(Set<TransactionDTO> transactions) {
        this.transactions = transactions;
    }

    public void addTransaction(Transaction transaction) {
        addTransaction(new TransactionDTO(transaction));
    }

    public void addTransaction(TransactionDTO transaction) {
        this.transactions.add(transaction);
    }

    public void addAllTransactions(List<Transaction> transactions) {
        addAllTransactionsDTO(transactions.stream().map(TransactionDTO::new).collect(Collectors.toList()));
    }

    public void addAllTransactionsDTO(List<TransactionDTO> transactions) {
        this.transactions.addAll(transactions);
    }
}
