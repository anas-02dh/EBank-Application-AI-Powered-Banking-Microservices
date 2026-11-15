package com.oracle.ebankservice;

import com.oracle.ebankservice.entities.BankAccount;
import com.oracle.ebankservice.service.EBankService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients
public class EbankServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EbankServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(EBankService eBankService) {
        return args -> {

            for(int i = 1; i<= 5; i++) {
                for(int j=0; j<6; j++) {
                    eBankService.save(BankAccount.builder()
                                    .type(Math.random() > 0.5 ? "Current-account" : "Saving-account")
                                    .balance(1000 + Math.random()*60000)
                                    .customerId(i)
                            .build());
                }
            }

        };
    }
}
