package com.example.electronictrading.service.impl;

import com.example.electronictrading.entity.Cart;

public interface CartService {
    Cart getCart(Integer cartId);
    Cart updateCart(Integer cartId, Cart cart);
    void emptyCart(Integer cartId);
}
