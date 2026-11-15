package com.oracle.customerservice.controllers;


import com.oracle.customerservice.entities.Customer;
import com.oracle.customerservice.services.CustomerService;
import jakarta.ws.rs.ext.ParamConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CustomerRestController {

    private final CustomerService customerService;


    @GetMapping("/customers")
    public List<Customer> getAllCustomers() {
        return  customerService.getAllCustomers();
    }

    @GetMapping("/customers/{id}")
    public Customer getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id);
    }

    @PostMapping("/customers")
    public Customer save(@RequestBody Customer customer){
        return customerService.save(customer);
    }
}
