package com.example.electronictrading;

import com.example.electronictrading.controller.OrderController;
import com.example.electronictrading.entity.Customer;
import com.example.electronictrading.entity.Order;
import com.example.electronictrading.service.impl.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderController.class)
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @Test
    @WithMockUser(username = "yilmaz", roles = {"admin"})
    void placeOrder_ValidOrder_ReturnsOrder() throws Exception {
        Order order = new Order();
        order.setId(1L);
        order.setTotalPrice(BigDecimal.TEN);
        order.setCreatedAt(LocalDateTime.now());

        when(orderService.placeOrder(any(Order.class))).thenReturn(order);

        mockMvc.perform(post("/api/order/place")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"totalPrice\":10}"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "yilmaz", roles = {"admin"})
    void getOrderForCode_ValidCode_ReturnsOrder() throws Exception {
        Order order = new Order();
        order.setId(1L);
        order.setTotalPrice(BigDecimal.TEN);
        order.setCreatedAt(LocalDateTime.now());

        when(orderService.getOrderForCode("CODE123")).thenReturn(order);

        mockMvc.perform(get("/api/order/CODE123"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "yilmaz", roles = {"admin"})
    void getAllOrdersForCustomer_ValidId_ReturnsOrders() throws Exception {
        List<Order> orders = new ArrayList<>();
        Order order = new Order();
        order.setId(1L);
        order.setTotalPrice(BigDecimal.TEN);
        order.setCreatedAt(LocalDateTime.now());
        orders.add(order);

        when(orderService.getAllOrdersForCustomer(any(Customer.class))).thenReturn(orders);

        mockMvc.perform(get("/api/order/customer/1"))
                .andExpect(status().isOk());
    }
}
