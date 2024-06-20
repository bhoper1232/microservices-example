package com.bhoper.controller;

import com.bhoper.dto.ProductRequest;
import com.bhoper.dto.ProductResponse;
import com.bhoper.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<?> createProduct(@Valid @RequestBody ProductRequest productRequest,
                                              BindingResult bindingResult) throws BindException{
        if (bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        } else {
            productService.createProduct(productRequest);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
    }

    @GetMapping
    public List<ProductResponse> getAllProducts() {
        return productService.getAllProducts();
    }

}
