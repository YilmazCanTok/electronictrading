package com.example.electronictrading.controller;

import com.example.electronictrading.entity.Product;
import com.example.electronictrading.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/Product")
public class ProductController {
    private final ProductService productService;
    private final List<Product> productList = new ArrayList<>();

    @GetMapping(value = "/all")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping(value = "/{id}")
    public Product getProduct(@PathVariable @Min(1) Integer id) {
        return productService.getProduct(id);
    }

    @PostMapping(value = "/create")
    public void addProduct(@Valid @RequestBody Product product) {
        for (Product existingProduct : productList) {
            if (existingProduct.getId() == product.getId()) {
                throw new IllegalArgumentException("A product with id " + product.getId() + " already exists.");
            }
        }
        productList.add(product);
        productService.addProduct(product);
    }

    @PutMapping(value = "/update")
    public Product updateProduct(@Valid @RequestBody Product product) {
        return productService.updateProduct(product);
    }

    @DeleteMapping(value = "/delete")
    public boolean deleteProduct(@RequestParam @Min(1) Integer id) {
        return productService.deleteProduct(id);
    }
}

