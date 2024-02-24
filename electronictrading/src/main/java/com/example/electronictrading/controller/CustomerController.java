package com.example.electronictrading.controller;

import com.example.electronictrading.entity.Customer;
import com.example.electronictrading.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customer")
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping(value = "/all")
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping(value = "/{id}")
    public Customer getCustomer(@PathVariable @Min(1) Integer id) {
        return customerService.getCustomer(id);
    }

    @PostMapping(value = "/create")
    public void addCustomer(@Valid @RequestBody Customer customer) {
        customerService.addCustomer(customer);
    }

    @PutMapping(value = "/update")
    public Customer updateCustomer(@Valid @RequestBody Customer customer) {
        return customerService.updateCustomer(customer);
    }

    @DeleteMapping(value = "/delete")
    public boolean deleteCustomer(@RequestParam @Min(1) Integer id) {
        return customerService.deleteCustomer(id);
    }
}
