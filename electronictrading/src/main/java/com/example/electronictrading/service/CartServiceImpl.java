package com.example.electronictrading.service;

import com.example.electronictrading.entity.Cart;
import com.example.electronictrading.entity.Order;
import com.example.electronictrading.entity.Product;
import com.example.electronictrading.exception.NotFoundException;
import com.example.electronictrading.repository.CartRepository;
import com.example.electronictrading.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.time.LocalDateTime;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final ProductService productService;

    private final OrderRepository orderRepository;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository, ProductService productService, OrderRepository orderRepository) {
        this.cartRepository = cartRepository;
        this.productService = productService;
        this.orderRepository = orderRepository;
    }

    @Override
    public Cart getCart(int cartId) {
        return cartRepository.findById(cartId)
                .orElseThrow(() -> new NotFoundException("Cart not found with id: " + cartId));
    }

    public Cart addToCart(int cartId, int productId, int quantity) {

        Cart cart = getCart(cartId);

        Product product = productService.getProduct(productId);


        if (product.getStock() >= quantity) {

            Order order = new Order();
            order.setCart(cart);
            order.setCustomer(cart.getCustomer()); // Siparişin müşterisi, sepetin müşterisi olacak
            order.setTotalPrice(product.getPrice().multiply(BigDecimal.valueOf(quantity))); // Siparişin toplam fiyatı
            order.setOrderCode(generateOrderCode()); // Siparişin kodunu oluştur
            order.setCreatedAt(LocalDateTime.now()); // Siparişin oluşturulma tarihi
            order.setUpdatedAt(LocalDateTime.now());
            order.setQuantity(quantity);
            order.setProduct(product);

            cart.getOrderList().add(order);
            order.setCart(cart);

            BigDecimal totalPrice = cart.getTotalPrice();
            totalPrice = totalPrice.add(product.getPrice().multiply(BigDecimal.valueOf(quantity)));
            cart.setTotalPrice(totalPrice);
            orderRepository.save(order);

            return updateCart(cart);
        } else {
            throw new RuntimeException("Stok yetersiz!");
        }
    }
    public String generateOrderCode(){
        String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        SecureRandom secureRandom = new SecureRandom();
            StringBuilder sb = new StringBuilder(5);
            for (int i = 0; i < 5; i++) {
                int randomIndex = secureRandom.nextInt(CHARACTERS.length());
                sb.append(CHARACTERS.charAt(randomIndex));
            }
            return sb.toString();
    }

    public Cart updateCart(Cart cart) {
        // Sepetin güncellenmesi ve kaydedilmesi işlemleri burada yapılabilir.
        return cartRepository.save(cart);
    }
    @Override
    public void emptyCart(int cartId) {
        Cart existingCart = getCart(cartId);
        existingCart.getOrderList().clear();
        existingCart.setTotalPrice(BigDecimal.valueOf(0));
        cartRepository.save(existingCart);
    }
}
