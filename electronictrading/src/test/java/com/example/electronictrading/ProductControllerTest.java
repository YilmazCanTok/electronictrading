package com.example.electronictrading;

import com.example.electronictrading.controller.ProductController;
import com.example.electronictrading.entity.Product;
import com.example.electronictrading.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.time.LocalDateTime;
import java.util.Arrays;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @Autowired
    private MockMvc mockMvc;

    private Product testProduct;

    @BeforeEach
    public void setUp() {
        testProduct = new Product();
        testProduct.setId(1);
        testProduct.setName("Test Product");
        testProduct.setBrand("Test Brand");
        testProduct.setPrice(BigDecimal.valueOf(100.00));
        testProduct.setStock(10);
        testProduct.setAddingDate(LocalDateTime.now());
    }

    @Test
    public void getAllProductsTest() throws Exception {
        when(productService.getAllProducts()).thenReturn(Arrays.asList(testProduct));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/Product/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(testProduct.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value(testProduct.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].brand").value(testProduct.getBrand()))
                .andDo(print());
    }

    @Test
    public void getProductTest() throws Exception {
        when(productService.getProduct(1)).thenReturn(testProduct);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/Product/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(testProduct.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(testProduct.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.brand").value(testProduct.getBrand()))
                .andDo(print());
    }

    @Test
    public void addProductTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/Product/create")
                        .content(asJsonString(testProduct))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print());
    }

    @Test
    public void updateProductTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/api/Product/update")
                        .content(asJsonString(testProduct))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(testProduct.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(testProduct.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.brand").value(testProduct.getBrand()))
                .andDo(print());
    }

    @Test
    public void deleteProductTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/Product/delete")
                        .param("id", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print());
    }

    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
