package com.example.electronictrading.service.impl;

import com.example.electronictrading.entity.Customer;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface CustomerService {
    Customer getCustomer(Integer id);
    List<Customer> getAllCustomers();
    void addCustomer(@RequestBody Customer customer);
    Customer updateCustomer(@RequestBody Customer customer);
    boolean deleteCustomer(Integer id);
}
