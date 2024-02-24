package com.example.electronictrading.listener;

import com.example.electronictrading.entity.Cart;
import com.example.electronictrading.entity.Customer;

import javax.persistence.*;
import java.math.BigDecimal;

public class CustomerListener {
    @PrePersist
    public void onCustomerCreate(Customer customer) {
        Cart cart = new Cart();
        cart.setTotalPrice(BigDecimal.ZERO); // Varsayılan olarak 0.00 değeri atanıyor
        customer.setCart(cart);
    }
}
