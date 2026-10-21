package com.kafka.producer_1.services;

import com.kafka.producer_1.dtos.ProductDto;
import com.kafka.producer_1.models.Product;
import com.kafka.producer_1.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("productService")
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public void createProduct(ProductDto product) {
        Product newProduct = new Product();
        newProduct.setName(product.name());
        newProduct.setStatus(product.status());
        newProduct.setAmount(product.amount());
        newProduct.setPrice(product.price());
        productRepository.save(newProduct);
    }
}
