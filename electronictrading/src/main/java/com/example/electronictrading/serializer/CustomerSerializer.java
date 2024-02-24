package com.example.electronictrading.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.example.electronictrading.entity.Customer;

import java.io.IOException;


public class CustomerSerializer extends JsonSerializer<Customer> {
    @Override
    public void serialize(Customer customer, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", customer.getId());
        jsonGenerator.writeStringField("firstName", customer.getFirstName());
        jsonGenerator.writeStringField("lastName", customer.getLastName());
        jsonGenerator.writeStringField("eMail", customer.getEMail());
        jsonGenerator.writeStringField("phoneNumber", customer.getPhoneNumber());
        jsonGenerator.writeStringField("address", customer.getAddress());
        jsonGenerator.writeStringField("city", customer.getCity());
        jsonGenerator.writeStringField("subscribeDate", customer.getSubscribeDate().toString());
        jsonGenerator.writeEndObject();
    }
}
