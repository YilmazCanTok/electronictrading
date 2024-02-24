package com.example.electronictrading.service.impl;

import com.example.electronictrading.entity.Customer;
import com.example.electronictrading.exception.NotFoundException;
import com.example.electronictrading.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService{
    private final CustomerRepository customerRepository;
    @Override
    public Customer getCustomer(Integer id) {
        Optional<Customer> byId = customerRepository.findById(id);
        return byId.orElseThrow(() -> new NotFoundException("Customer"));
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public void addCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public boolean deleteCustomer(Integer id) {
        customerRepository.delete(getCustomer(id));
        return true;
    }

}
