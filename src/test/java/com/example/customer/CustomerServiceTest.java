package com.example.customer;

import com.example.customer.exceptions.CustomerNotFoundException;
import com.example.customer.model.Customer;
import com.example.customer.repository.CustomerRepository;
import com.example.customer.service.CustomerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {
    @Mock
    private CustomerRepository customerRepository;
    @InjectMocks
    private CustomerService customerService;
    @Test
    public void whenSaveCustomer_shouldReturnUser(){
        Customer customer = new Customer();
        customer.setCustomerName("Test Name");
        when(customerRepository.save(ArgumentMatchers.any(Customer.class))).thenReturn(customer);
        Customer created = customerService.createNewCustomer(customer);
        assertThat(created.getCustomerName()).isSameAs(customer.getCustomerName());
        verify(customerRepository).save(customer);
    }
    @Test
    public void whenGivenId_shouldReturnCustomer_ifFound(){
        Customer customer = new Customer();
        customer.setCustomerNumber(10000000L);
        when(customerRepository.findById(customer.getCustomerNumber())).thenReturn(Optional.of(customer));
        Customer expected = customerService.listCustomer(customer.getCustomerNumber()).get();
        assertThat(expected).isSameAs(customer);
        verify(customerRepository).findById(customer.getCustomerNumber());
    }
    @Test(expected = CustomerNotFoundException.class)
    public void should_throw_exception_when_customer_doesnt_exist(){
        Customer customer = new Customer();
        customer.setCustomerNumber(8989L);
        customer.setCustomerName("Test Name");
        given(customerRepository.findById(anyLong())).willReturn(Optional.ofNullable(null));
        customerService.deleteCustomer(customer.getCustomerNumber());
    }





}
