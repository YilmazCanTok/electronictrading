package com.example.electronictrading.service.impl;

import com.example.electronictrading.entity.Cart;
import com.example.electronictrading.exception.NotFoundException;
import com.example.electronictrading.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;

@Service
@Transactional
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public Cart getCart(Integer cartId) {
        return cartRepository.findById(cartId)
                .orElseThrow(() -> new NotFoundException("Cart not found with id: " + cartId));
    }

    @Override
    public Cart updateCart(Integer cartId, Cart cart) {
        Cart existingCart = getCart(cartId);
        existingCart.setCustomer(cart.getCustomer());
        existingCart.setOrderList(cart.getOrderList());
        return cartRepository.save(existingCart);
    }

    @Override
    public void emptyCart(Integer cartId) {
        Cart cart = getCart(cartId);
        cart.setOrderList(Collections.emptyList());
        cartRepository.save(cart);
    }
}