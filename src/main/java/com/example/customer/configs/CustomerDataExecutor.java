package com.example.customer.configs;

import com.example.customer.model.AccountType;
import com.example.customer.model.Customer;
import com.example.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


import java.util.Arrays;
import java.util.List;

@Component
public class CustomerDataExecutor implements CommandLineRunner {

    @Autowired
    private CustomerService customerService;

    @Override
    public void run(String... args) {
        customerService.deleteAllCustomers();
        List<Customer> customers = Arrays.asList(
                Customer.builder().customerName("John").customerMobile("102903909").customerEmail("tom@gmail.com").address1(" adjkjkg").address2("sdmkfnfk").accountType(AccountType.C).accountNumber(30023930L).availableBalance(333L).build(),
                Customer.builder().customerName("Melisa").customerMobile("10122039").customerEmail("dsdfdm@gmail.com").address1(" adjkjkg").address2("sdmkfnfk").accountType(AccountType.C).accountNumber(344323930L).availableBalance(3133L).build(),
                Customer.builder().customerName("Kori").customerMobile("102123039").customerEmail("sdfom@gmail.com").address1(" adjkjkg").address2("sdmkfnfk").accountType(AccountType.C).accountNumber(305663930L).availableBalance(433L).build()
        );
        customerService.saveAllCustomers(customers);
    }
}
