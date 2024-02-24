package com.example.electronictrading;

import com.example.electronictrading.controller.CustomerController;
import com.example.electronictrading.entity.Customer;
import com.example.electronictrading.service.CustomerService;
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
import java.util.Arrays;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    @Autowired
    private MockMvc mockMvc;

    private Customer testCustomer;

    @BeforeEach
    public void setUp() {
        testCustomer = new Customer();
        testCustomer.setId(1);
        testCustomer.setFirstName("John");
        testCustomer.setLastName("Doe");
        testCustomer.setEMail("john.doe@example.com");
        testCustomer.setPhoneNumber("123456789");
        testCustomer.setAddress("123 Main St");
        testCustomer.setCity("New York");
        // Diğer özellikler de burada ayarlanabilir
    }

    @Test
    public void getAllCustomersTest() throws Exception {
        when(customerService.getAllCustomers()).thenReturn(Arrays.asList(testCustomer));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/customer/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(testCustomer.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].firstName").value(testCustomer.getFirstName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].lastName").value(testCustomer.getLastName()))
                .andDo(print());
    }

    @Test
    public void getCustomerTest() throws Exception {
        when(customerService.getCustomer(1)).thenReturn(testCustomer);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/customer/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(testCustomer.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value(testCustomer.getFirstName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value(testCustomer.getLastName()))
                .andDo(print());
    }

    @Test
    public void addCustomerTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/customer/create")
                        .content(asJsonString(testCustomer))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print());
    }

    @Test
    public void updateCustomerTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/api/customer/update")
                        .content(asJsonString(testCustomer))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(testCustomer.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value(testCustomer.getFirstName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value(testCustomer.getLastName()))
                .andDo(print());
    }

    @Test
    public void deleteCustomerTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/customer/delete")
                        .param("id", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print());
    }

    // Yardımcı metotlar
    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
