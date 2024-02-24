package com.example.electronictrading.controller;

import com.example.electronictrading.entity.Order;
import com.example.electronictrading.entity.Customer;
import com.example.electronictrading.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/code/{orderCode}")
    public Order getOrderForCode(@PathVariable String orderCode) {
        return orderService.getOrderForCode(orderCode);
    }

    @GetMapping("/customer/{customerId}")
    public List<Order> getAllOrdersForCustomer(@PathVariable @Min(1) Integer customerId) {
        Customer customer = new Customer();
        customer.setId(customerId);
        return orderService.getAllOrdersForCustomer(customer);
    }
}
