package com.bhoper.service;

import com.bhoper.dto.ProductRequest;
import com.bhoper.dto.ProductResponse;
import com.bhoper.model.Product;
import com.bhoper.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    public void createProduct(ProductRequest productRequest) {
        Product product = new Product(productRequest.name(),
                productRequest.description(), productRequest.price());

        productRepository.save(product);
        log.info("Product %s is saved ".formatted(product.getId()));
    }

    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll().stream()
                .map(this::mapToProductResponse)
                .toList();
    }

    private ProductResponse mapToProductResponse(Product product) {
        return new ProductResponse(product.getId(), product.getName(), product.getDescription(),
                product.getPrice());
    }
}
