package com.example.perfumeShop.controller;

import com.example.perfumeShop.dto.ProductRequest;
import com.example.perfumeShop.dto.ProductResponse;
import com.example.perfumeShop.entity.Product;
import com.example.perfumeShop.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ProductResponse> add(@Valid @RequestBody ProductRequest request ){
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.add(request));
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAll() {
        System.out.println("called the controller");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productService.getAll());
    }
}
