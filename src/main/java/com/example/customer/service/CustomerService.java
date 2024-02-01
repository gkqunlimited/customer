package com.example.customer.service;

import com.example.customer.exceptions.CustomerNotFoundException;
import com.example.customer.model.Customer;
import com.example.customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;
    public Customer createNewCustomer(Customer customer){
        return customerRepository.save(customer);
    }

    public Optional<Customer> listCustomer(Long id){
        return customerRepository.findById(id);
    }

    public void deleteCustomer(Long id){
        customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer Not Found"));
        customerRepository.deleteById(id);
    }

    public void deleteAllCustomers() {
        customerRepository.deleteAll();
    }

    public void saveAllCustomers(List<Customer> users) {
        customerRepository.saveAll(users);
    }




}
