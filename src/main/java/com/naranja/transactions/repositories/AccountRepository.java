package com.naranja.transactions.repositories;

import com.naranja.transactions.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Long countByNameAndDni(String name, Integer dni);
}
