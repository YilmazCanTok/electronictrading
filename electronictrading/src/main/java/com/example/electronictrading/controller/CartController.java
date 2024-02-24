package com.example.electronictrading.controller;


import com.example.electronictrading.entity.Cart;
import com.example.electronictrading.service.impl.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cart")
public class CartController {
    private final CartService cartService;

    @GetMapping("/{cartId}")
    public Cart getCart(@PathVariable @Min(1) Integer cartId) {
        return cartService.getCart(cartId);
    }

    @PutMapping("/{cartId}")
    public Cart updateCart(@PathVariable @Min(1) Integer cartId, @Valid @RequestBody Cart cart) {
        return cartService.updateCart(cartId, cart);
    }

    @DeleteMapping("/{cartId}")
    public void emptyCart(@PathVariable @Min(1) Integer cartId) {
        cartService.emptyCart(cartId);
    }
}
