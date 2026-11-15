package com.oracle.ebankservice.controllers;

import com.oracle.ebankservice.entities.BankAccount;
import com.oracle.ebankservice.repositories.BankAccountRepository;
import com.oracle.ebankservice.service.EBankService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class EBankRestController {
    private final EBankService eBankService;


    @GetMapping("/accounts")
    public List<BankAccount> getAllBankAccounts() {


        return eBankService.getAllBankAccounts();
    }

    @GetMapping("/accounts/{id}")
    public BankAccount getBankAccountById(@PathVariable String id) {
        return eBankService.getBankAccountById(id);
    }

    @PostMapping("/accounts")
    public BankAccount save(@RequestBody BankAccount account) {
        return eBankService.save(account);
    }
}
