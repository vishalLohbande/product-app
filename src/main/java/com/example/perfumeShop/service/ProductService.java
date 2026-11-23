package com.example.perfumeShop.service;

import com.example.perfumeShop.dto.ProductRequest;
import com.example.perfumeShop.dto.ProductResponse;
import com.example.perfumeShop.entity.Product;
import com.example.perfumeShop.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductResponse add(ProductRequest request){
        Product product = productRepository.save(mapToEntity(request));
        return mapToRecord(product);
    }

    protected Product mapToEntity(ProductRequest request){
        return new Product(
                request.id(),
                request.name(),
                request.price()
        );
    }

    protected ProductResponse mapToRecord(Product product){
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getPrice(),
                "product added successfully"
        );
    }
}
