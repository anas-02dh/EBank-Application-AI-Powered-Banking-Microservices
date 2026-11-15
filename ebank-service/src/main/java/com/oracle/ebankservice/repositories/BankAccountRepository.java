package com.oracle.ebankservice.repositories;

import com.oracle.ebankservice.entities.BankAccount;
import com.oracle.ebankservice.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BankAccountRepository extends JpaRepository<BankAccount,String> {
    List<BankAccount> findByCustomerId(long customerId);
}
