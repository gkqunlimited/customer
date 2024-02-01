package com.example.customer.controller;

import com.example.customer.model.Customer;
import com.example.customer.result.PayLoad;
import com.example.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("/api/v1/account")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @PostMapping
    public ResponseEntity<Map> createCustomer(@RequestBody @Valid Customer customer, BindingResult bindingResult) {

        try {
            Map<String, Object> object = new LinkedHashMap<>();

            if(bindingResult.hasErrors()){
                object.put("transactionStatusCode", HttpStatus.BAD_REQUEST.value());
                object.put("transactionStatusDescription", PayLoad.getMessage(bindingResult.getFieldError().getField()));
                return ResponseEntity.badRequest().body(object);
            }

            Customer savedCustomer = customerService.createNewCustomer(customer);
            object.put("customerNumber ", savedCustomer.getCustomerNumber());
            object.put("transactionStatusCode", HttpStatus.CREATED.value());
            object.put("transactionStatusDescription", PayLoad.getMessage("created"));

            return ResponseEntity.ok().body(object);

        } catch ( Exception e) {
            return ResponseEntity.internalServerError().build();

        }
    }
    @GetMapping("/{customerNumber}")
    public ResponseEntity<Map> getCustomer(@PathVariable("customerNumber") Long customerNumber){
        try {
            Map<String, Object> object = new LinkedHashMap<>();
            Optional<Customer> customer = customerService.listCustomer(customerNumber);
            if(customer.isPresent()){
                object.put("customerNumber", customer.get().getCustomerNumber());
                object.put("customerName", customer.get().getCustomerName());
                object.put("customerMobile", customer.get().getCustomerMobile());
                object.put("address1", customer.get().getAddress1());
                object.put("address2", customer.get().getAddress2());

                Map<String, Object> savings = new LinkedHashMap<>();
                savings.put("accountNumber", customer.get().getAccountNumber());
                savings.put("accountType", customer.get().getAccountType().label);
                savings.put("availableBalance", customer.get().getAvailableBalance());

                object.put("savings", savings);
                object.put("transactionStatusCode", HttpStatus.FOUND.value());
                object.put("transactionStatusDescription",PayLoad.getMessage("found"));

                return ResponseEntity.ok().body(object);
            }else {
                object.put("transactionStatusCode", HttpStatus.UNAUTHORIZED.value());
                object.put("transactionStatusDescription",PayLoad.getMessage("notFound"));
                return ResponseEntity.badRequest().body(object);
            }
        } catch ( Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
