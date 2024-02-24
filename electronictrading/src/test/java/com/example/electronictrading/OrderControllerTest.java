package com.example.electronictrading;

import com.example.electronictrading.controller.OrderController;
import com.example.electronictrading.entity.Customer;
import com.example.electronictrading.entity.Order;
import com.example.electronictrading.service.OrderService;
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

@WebMvcTest(OrderController.class)
public class OrderControllerTest {

    @Mock
    private OrderService orderService;

    @InjectMocks
    private OrderController orderController;

    @Autowired
    private MockMvc mockMvc;

    private Order testOrder;

    @BeforeEach
    public void setUp() {
        testOrder = new Order();
        testOrder.setId(1);
        // Diğer özellikler burada ayarlanabilir
    }

    @Test
    public void getOrderForCodeTest() throws Exception {
        String orderCode = "123456";
        when(orderService.getOrderForCode(orderCode)).thenReturn(testOrder);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/order/code/" + orderCode)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(testOrder.getId()))
                .andDo(print());
    }

    @Test
    public void getAllOrdersForCustomerTest() throws Exception {
        Customer customer = new Customer();
        customer.setId(1);

        when(orderService.getAllOrdersForCustomer(customer)).thenReturn(Arrays.asList(testOrder));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/order/customer/" + customer.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(testOrder.getId()))
                .andDo(print());
    }
}
