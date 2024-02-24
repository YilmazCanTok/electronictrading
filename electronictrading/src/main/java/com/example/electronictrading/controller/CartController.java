package com.example.electronictrading.controller;

import com.example.electronictrading.entity.Cart;
import com.example.electronictrading.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/{cartId}")
    public Cart getCart(@PathVariable int cartId) {
        return cartService.getCart(cartId);
    }

    @PostMapping("/add")
    public ResponseEntity<Cart> addToCart(@RequestParam int cartId, @RequestParam int productId, @RequestParam int quantity) {
        Cart cart = cartService.addToCart(cartId, productId, quantity);
        return ResponseEntity.ok(cart);
    }

    @DeleteMapping("/{cartId}")
    public void emptyCart(@PathVariable int cartId) {
        cartService.emptyCart(cartId);
    }


}
