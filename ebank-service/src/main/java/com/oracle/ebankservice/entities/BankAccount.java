package com.oracle.ebankservice.entities;

import com.oracle.ebankservice.model.Customer;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Service;

import java.util.Date;


@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private Date createdAt;
    private double balance;
    private String type;
    private long customerId;
    @Transient
    private Customer customer;
}
