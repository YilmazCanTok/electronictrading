package com.example.electronictrading;

import com.example.electronictrading.entity.Product;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;


import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithMockUser(username = "yilmaz", roles = {"admin"})
    void givenAdminRole_whenGetAllProducts_thenStatus200() throws Exception {
        mockMvc.perform(get("/api/product"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "ahmet", roles = {"user"})
    void givenUserRole_whenGetAllProducts_thenStatus403() throws Exception {
        mockMvc.perform(get("/api/product"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "mehmet", roles = {"user"})
    void givenUserRole_whenAddProduct_thenStatus403() throws Exception {
        Product product = new Product();
        product.setName("Test Product");
        product.setBrand("Test Brand");
        product.setPrice(BigDecimal.valueOf(100.0));
        product.setStock((int) 10);

        mockMvc.perform(post("/api/product/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "yilmaz", roles = {"admin"})
    void givenAdminRole_whenUpdateProduct_thenStatus200() throws Exception {
        Product product = new Product();
        product.setId(1);
        product.setName("Updated Product");
        product.setBrand("Updated Brand");
        product.setPrice(BigDecimal.valueOf(200.0));
        product.setStock((int) 20);

        mockMvc.perform(put("/api/product/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isOk());
    }
}
