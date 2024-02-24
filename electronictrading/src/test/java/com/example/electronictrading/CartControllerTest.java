package com.example.electronictrading;

import com.example.electronictrading.controller.CartController;
import com.example.electronictrading.entity.Cart;
import com.example.electronictrading.service.impl.CartService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CartController.class)
public class CartControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CartService cartService;

    @Test
    @WithMockUser(username = "yilmaz", roles = {"admin"})
    void givenAdminRole_whenGetCart_thenReturnsCart() throws Exception {
        Cart cart = new Cart();
        cart.setId(1);
        cart.setOrderList(Collections.emptyList());

        when(cartService.getCart(1)).thenReturn(cart);

        mockMvc.perform(get("/api/cart/1"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "ahmet", roles = {"user"})
    void givenUserRole_whenGetCart_thenReturnsForbidden() throws Exception {
        mockMvc.perform(get("/api/cart/1"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "mehmet", roles = {"user"})
    void givenUserRole_whenUpdateCart_thenReturnsForbidden() throws Exception {
        mockMvc.perform(put("/api/cart/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"orderList\":[]}"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "yilmaz", roles = {"admin"})
    void givenAdminRole_whenEmptyCart_thenReturnsNoContent() throws Exception {
        mockMvc.perform(delete("/api/cart/1"))
                .andExpect(status().isNoContent());
    }
}
