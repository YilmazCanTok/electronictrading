package com.example.electronictrading;

import com.example.electronictrading.controller.CartController;
import com.example.electronictrading.entity.Cart;
import com.example.electronictrading.service.CartService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.math.BigDecimal;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(CartController.class)
public class CartControllerTest {

    @Mock
    private CartService cartService;

    @InjectMocks
    private CartController cartController;

    @Autowired
    private MockMvc mockMvc;

    private Cart testCart;

    @BeforeEach
    public void setUp() {
        testCart = new Cart();
    }

    @Test
    public void getCartTest() throws Exception {
        int cartId = 1;
        when(cartService.getCart(cartId)).thenReturn(testCart);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/cart/" + cartId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(testCart.getId()))
                .andDo(print());
    }

    @Test
    public void addToCartTest() throws Exception {
        int cartId = 1;
        int productId = 1;
        int quantity = 2;
        when(cartService.addToCart(cartId, productId, quantity)).thenReturn(testCart);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/cart/add")
                        .param("cartId", String.valueOf(cartId))
                        .param("productId", String.valueOf(productId))
                        .param("quantity", String.valueOf(quantity))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(testCart.getId()))
                // Diğer özellikler buraya eklenebilir
                .andDo(print());
    }

    @Test
    public void emptyCartTest() throws Exception {
        int cartId = 1;

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/cart/" + cartId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                // Diğer özellikler buraya eklenebilir
                .andDo(print());
    }
}
