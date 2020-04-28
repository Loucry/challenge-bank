package com.naranja.transactions.repositories;

import com.naranja.transactions.enums.TransactionType;
import com.naranja.transactions.models.Account;
import com.naranja.transactions.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Set;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query(nativeQuery = true, value = "SELECT COUNT(t.id) FROM transaction_table t WHERE t.transaction_time >= (?1 - INTERVAL ?2 SECOND)")
    Long countHighFrecuency(LocalDateTime transactionTime, Long seconds_interval);

    Long countByTransactionTypeAndCommerceAndAccountAndAmount(TransactionType transactionType, String commerce, Account account, Long amount);

    @Query(value = "SELECT t FROM Transaction t WHERE t.account.id = ?1")
    Set<Transaction> findByAccountId(Long accountId);
}
