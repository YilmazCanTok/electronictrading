package com.example.electronictrading.service;

import com.example.electronictrading.entity.Cart;


public interface CartService {
    Cart getCart(int cartId);

    Cart addToCart(int cartId, int productId, int quantity);

    void emptyCart(int cartId);
}

