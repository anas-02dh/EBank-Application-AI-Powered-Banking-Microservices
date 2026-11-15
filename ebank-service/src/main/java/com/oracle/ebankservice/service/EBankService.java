package com.oracle.ebankservice.service;


import com.oracle.ebankservice.entities.BankAccount;
import com.oracle.ebankservice.feign.CustomerRestClient;
import com.oracle.ebankservice.model.Customer;
import com.oracle.ebankservice.repositories.BankAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.mcp.annotation.McpTool;
import org.springframework.ai.mcp.annotation.McpToolParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service

public class EBankService {

    private final BankAccountRepository bankAccountRepository;
    private final CustomerRestClient customerRestClient;


    public EBankService(BankAccountRepository bankAccountRepository, CustomerRestClient customerRestClient) {
        this.bankAccountRepository = bankAccountRepository;
        this.customerRestClient = customerRestClient;
    }



    @McpTool(description = "Get all Bank Accounts")
    public List<BankAccount> getAllBankAccounts() {

        List<BankAccount> accounts = bankAccountRepository.findAll();
        for(BankAccount acc : accounts) {
            acc.setCustomer(customerRestClient.getCustomerById(acc.getCustomerId()));
        }

        return  accounts;
    }

    @McpTool(description = "Get a bank account by id")
    public BankAccount getBankAccountById(@McpToolParam(description = "The bank account id") String id) {
        BankAccount bankAccount = bankAccountRepository.findById(id).orElseThrow(()-> new RuntimeException("Bank Account Not Found !!"));
        bankAccount.setCustomer(customerRestClient.getCustomerById(bankAccount.getCustomerId()));
        return bankAccount;
    }


    @McpTool(description = "Save a new bank account")
    public BankAccount save(@McpToolParam(description = "The bank account to save (balance, type, customerId") BankAccount account) {

        try {
            Customer customer = customerRestClient.getCustomerById(account.getCustomerId());
            account.setCustomer(customer);
            account.setCreatedAt(new Date());
            return bankAccountRepository.save(account);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }
}
