package com.example.electronictrading.service;

import com.example.electronictrading.entity.Customer;
import com.example.electronictrading.entity.Order;

import java.util.List;

public interface OrderService {

    Order placeOrder(Order order);
    Order getOrderForCode(String orderCode);
    List<Order> getAllOrdersForCustomer(Customer customer);

}
