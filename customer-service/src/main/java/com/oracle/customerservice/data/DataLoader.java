package com.oracle.customerservice.data;

import com.oracle.customerservice.entities.Customer;
import com.oracle.customerservice.repositories.CustomerRepository;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class DataLoader {
    private CustomerRepository customerRepository;

    @PostConstruct
    private void loadData() {
        customerRepository.saveAll(
                List.of(
                        Customer.builder()
                                .name("Driouch")
                                .email("an.driouch@gmail.com")
                                .build(),

                        Customer.builder()
                                .name("Saif")
                                .email("yf.saif@gmail.com")
                                .build(),

                        Customer.builder()
                                .name("Benali")
                                .email("ah.benali@gmail.com")
                                .build(),

                        Customer.builder()
                                .name("Kebbaj")
                                .email("sar.kebbaj@gmail.com")
                                .build(),

                        Customer.builder()
                                .name("houda")
                                .email("hd.kebbaj@gmail.com")
                                .build()

                )

        );
    }
}
