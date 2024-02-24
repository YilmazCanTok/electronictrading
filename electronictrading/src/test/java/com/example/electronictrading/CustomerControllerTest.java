package com.example.electronictrading;

import com.example.electronictrading.controller.CustomerController;
import com.example.electronictrading.entity.Customer;
import com.example.electronictrading.service.impl.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    @Test
    @WithMockUser(username = "yilmaz", roles = {"admin"})
    void getAllCustomers_ReturnsCustomers() throws Exception {
        List<Customer> customers = new ArrayList<>();
        Customer customer = new Customer();
        customer.setId(1);
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setEMail("john@example.com");
        customer.setPhoneNumber("123456789");
        customer.setAddress("123 Main St");
        customer.setCity("New York");
        customer.setSubscribeDate(LocalDateTime.now());
        customers.add(customer);

        when(customerService.getAllCustomers()).thenReturn(customers);

        mockMvc.perform(get("/api/customer/all"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "yilmaz", roles = {"admin"})
    void getCustomer_ValidId_ReturnsCustomer() throws Exception {
        Customer customer = new Customer();
        customer.setId(1);
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setEMail("john@example.com");
        customer.setPhoneNumber("123456789");
        customer.setAddress("123 Main St");
        customer.setCity("New York");
        customer.setSubscribeDate(LocalDateTime.now());

        when(customerService.getCustomer(1)).thenReturn(customer);

        mockMvc.perform(get("/api/customer/1"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "yilmaz", roles = {"admin"})
    void addCustomer_ValidRequestBody_ReturnsCreated() throws Exception {
        Customer customer = new Customer();
        customer.setId(1);
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setEMail("john@example.com");
        customer.setPhoneNumber("123456789");
        customer.setAddress("123 Main St");
        customer.setCity("New York");
        customer.setSubscribeDate(LocalDateTime.now());

        mockMvc.perform(post("/api/customer/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"John\",\"surname\":\"Doe\",\"eMail\":\"john@example.com\",\"phoneNumber\":\"123456789\",\"address\":\"123 Main St\",\"city\":\"New York\"}"))
                .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser(username = "yilmaz", roles = {"admin"})
    void updateCustomer_ValidRequestBody_ReturnsCustomer() throws Exception {
        Customer customer = new Customer();
        customer.setId(1);
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setEMail("john@example.com");
        customer.setPhoneNumber("123456789");
        customer.setAddress("123 Main St");
        customer.setCity("New York");
        customer.setSubscribeDate(LocalDateTime.now());

        when(customerService.updateCustomer(any(Customer.class))).thenReturn(customer);

        mockMvc.perform(put("/api/customer/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"name\":\"John\",\"surname\":\"Doe\",\"eMail\":\"john@example.com\",\"phoneNumber\":\"123456789\",\"address\":\"123 Main St\",\"city\":\"New York\"}"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "yilmaz", roles = {"admin"})
    void deleteCustomer_ValidId_ReturnsNoContent() throws Exception {
        mockMvc.perform(delete("/api/customer/delete")
                        .param("id", "1"))
                .andExpect(status().isNoContent());
    }
}
