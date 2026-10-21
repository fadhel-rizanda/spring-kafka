package com.kafka.producer_1.services;

import com.kafka.producer_1.dtos.ProductDto;
import com.kafka.producer_1.exception.NotFoundException;
import com.kafka.producer_1.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service("productCacheService")
@Primary
@RequiredArgsConstructor
public class ProductCacheService {
    private final ProductRepository productRepository;
    private final AsyncService asyncService;

//    @Cacheable(value = "product", key = "#id") // Notes ini gw matiin soalnya kalo data sudah dicache argumen didalemnya tidak dijalankan sama sekali
    public ProductDto findById(UUID id) {
        asyncService.testAsync();
        asyncService.loadProduct().whenComplete((s, e) -> log.info("async: {}", s));
        return productRepository.findById(id)
                .map(p -> new ProductDto(p.getId(), p.getName(), p.getStatus(), p.getAmount(), p.getPrice()))
                .orElseThrow(() -> new NotFoundException("Product not found"));
    }

    @CachePut(value = "product", key = "#id")
    public ProductDto update(UUID id, ProductDto productDto) {
        return productRepository.findById(id).map(product -> {
                    product.setName(productDto.name());
                    product.setStatus(productDto.status());
                    product.setAmount(productDto.amount());
                    product.setPrice(productDto.price());
                    return product;
                })
                .map(productRepository::save)
                .map(p -> new ProductDto(p.getId(), p.getName(), p.getStatus(), p.getAmount(), p.getPrice()))
                .orElseThrow(() -> new NotFoundException("Product not found"));
    }

    //    @CacheEvict(value = "product", key = "#id")
//    public void evict(UUID id) {
//        return;
//    }
    @CacheEvict(value = "product", key = "#id")
    public void delete(UUID id) {
        productRepository.deleteById(id);
    }

    @CacheEvict(value = "product", allEntries = true)
    public void evictAll() {
        // ga perlu diisi, karena hanya untuk menghapus semua cache
    }
}
