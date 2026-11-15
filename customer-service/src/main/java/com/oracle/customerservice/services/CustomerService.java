package com.oracle.customerservice.services;


import com.oracle.customerservice.entities.Customer;
import com.oracle.customerservice.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.mcp.annotation.McpTool;
import org.springframework.ai.mcp.annotation.McpToolParam;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;


    @McpTool(description = "Get all customers")
    public List<Customer> getAllCustomers() {
        return  customerRepository.findAll();
    }

    @McpTool(description = "Get customer by id")
    public Customer getCustomerById(@McpToolParam(description = "The customer id") Long id) {
        return customerRepository.findById(id).orElseThrow(()-> new RuntimeException("Customer Not Found !!"));
    }

    @McpTool(description = "Save a new customer")
    public Customer save(@McpToolParam(description = "The Customer to save (name,email)") Customer customer){
        return customerRepository.save(customer);
    }
}
