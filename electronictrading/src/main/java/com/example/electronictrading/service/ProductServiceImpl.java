package com.example.electronictrading.service;

import com.example.electronictrading.entity.Product;
import com.example.electronictrading.exception.NotFoundException;
import com.example.electronictrading.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService{
    private final ProductRepository productRepository;
    @Override
    public Product getProduct(Integer id) {
        Optional<Product> byId = productRepository.findById(id);
        return byId.orElseThrow(() -> new NotFoundException("Product"));
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public void addProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public boolean deleteProduct(Integer id) {
        productRepository.delete(getProduct(id));
        return true;
    }
}
