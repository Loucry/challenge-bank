package com.naranja.transactions.services;

import com.naranja.transactions.dto.AccountDTO;
import com.naranja.transactions.exceptions.CustomValidationException;
import com.naranja.transactions.models.Account;
import com.naranja.transactions.models.Transaction;
import com.naranja.transactions.repositories.TransactionRepository;
import com.naranja.transactions.validator.TransactionValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Service
public class TransactionService implements ITransactionService {

    private final TransactionRepository transactionRepository;

    private final TransactionValidator transactionValidator;

    private final IAccountService accountService;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository, TransactionValidator transactionValidator, IAccountService accountService) {
        this.transactionRepository = transactionRepository;
        this.transactionValidator = transactionValidator;
        this.accountService = accountService;
    }

    @Override
    public AccountDTO createTransaction(Transaction transaction) throws CustomValidationException {
        Account account = accountService.getAccount(transaction.getAccountId());

        transaction = createSingleTransaction(transaction, account);
        transaction = transactionRepository.save(transaction);

        account = accountService.updateAmount(account);

        AccountDTO accountDTO = new AccountDTO(account);

        accountDTO.addTransaction(transaction);

        return accountDTO;
    }

    @Override
    public AccountDTO createTransactions(List<Transaction> transactions) throws CustomValidationException {
        if (transactions != null && !transactions.isEmpty()) {
            Account account = accountService.getAccount(transactions.get(0).getAccountId());

            for (Transaction transaction : transactions) {
                transaction = createSingleTransaction(transaction, account);
            }

            transactions = transactionRepository.saveAll(transactions);

            account = accountService.updateAmount(account);

            AccountDTO accountDTO = new AccountDTO(account);

            accountDTO.addAllTransactions(transactions);

            return accountDTO;
        } else {
            return null;
        }
    }

    @Override
    public Long highFrecuency(LocalDateTime transactionTime, Long seconds_interval) {
        return transactionRepository.countHighFrecuency(transactionTime, seconds_interval);
    }

    @Override
    public Long countDoubledTransaction(Transaction transaction) {
        return transactionRepository.countByTransactionTypeAndCommerceAndAccountAndAmount(transaction.getTransactionType(), transaction.getCommerce(), transaction.getAccount(), transaction.getAmount());
    }

    @Override
    public Set<Transaction> getTransactionsByAccountId(Long accountId) {
        return transactionRepository.findByAccountId(accountId);
    }

    @Override
    public Transaction getTransactionById(Long transactionId) {
        return transactionRepository.findById(transactionId).orElseThrow(() -> new RuntimeException("Transaction with id " + transactionId + " not found"));
    }

    private Transaction createSingleTransaction(Transaction transaction, Account account) throws CustomValidationException {
        transaction.setAccount(account);

        transactionValidator.validateCreateTransaction(transaction);

        switch (transaction.getTransactionType()) {
            case withdraw:
            case purchase:
                account.setAvailableAmount(account.getAvailableAmount()-transaction.getAmount());
                break;
            case deposit:
                account.setAvailableAmount(account.getAvailableAmount()+transaction.getAmount());
                break;
        }
        return transaction;
    }
}
