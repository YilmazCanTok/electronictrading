package com.example.electronictrading.repository;

import com.example.electronictrading.entity.Customer;
import com.example.electronictrading.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    Order findByOrderCode(String orderCode);
    List<Order> findByCustomer(Customer customer);
}
