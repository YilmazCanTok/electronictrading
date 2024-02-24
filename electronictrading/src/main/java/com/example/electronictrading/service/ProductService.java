package com.example.electronictrading.service;

import com.example.electronictrading.entity.Product;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ProductService {
    Product getProduct(Integer id);
    List<Product> getAllProducts();
    void addProduct(@RequestBody Product product);
    Product updateProduct(@RequestBody Product product);
    boolean deleteProduct(Integer id);
}
