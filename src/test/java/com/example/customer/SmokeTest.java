package com.example.customer;

import com.example.customer.controller.CustomerController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class SmokeTest {
    @Autowired
    private CustomerController customerController;

    @Test
    void contextLoads() {
        assertThat(customerController).isNotNull();
    }
}
