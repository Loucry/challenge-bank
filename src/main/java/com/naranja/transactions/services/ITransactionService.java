package com.naranja.transactions.services;

import com.naranja.transactions.dto.AccountDTO;
import com.naranja.transactions.exceptions.CustomValidationException;
import com.naranja.transactions.models.Transaction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Service
public interface ITransactionService {

    @Transactional
    AccountDTO createTransaction(Transaction transaction) throws CustomValidationException;

    @Transactional
    AccountDTO createTransactions(List<Transaction> transactions) throws CustomValidationException;

    @Transactional(readOnly = true)
    Long highFrecuency(LocalDateTime transactionTime, Long seconds_interval);

    @Transactional(readOnly = true)
    Long countDoubledTransaction(Transaction transaction);

    @Transactional(readOnly = true)
    Set<Transaction> getTransactionsByAccountId(Long accountId);

    @Transactional(readOnly = true)
    Transaction getTransactionById(Long transactionId);
}
