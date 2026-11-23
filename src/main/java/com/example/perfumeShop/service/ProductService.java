package com.example.perfumeShop.service;

import com.example.perfumeShop.KafkaConfig.AppConstant;
import com.example.perfumeShop.dto.ProductRequest;
import com.example.perfumeShop.dto.ProductResponse;
import com.example.perfumeShop.entity.Product;
import com.example.perfumeShop.repository.ProductRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private ProductRepository productRepository;
    private KafkaTemplate kafkaTemplate;

    public ProductService(ProductRepository productRepository, KafkaTemplate kafkaTemplate) {
        this.productRepository = productRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    public ProductResponse add(ProductRequest request){
        Product product = productRepository.save(mapToEntity(request));
        kafkaTemplate.send(AppConstant.PRODUCT_CREATED, mapToRecord(product));
        return mapToRecord(product);
    }

    public List<Product> getAll(){
        return productRepository.findAll();
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

    @KafkaListener(topics = AppConstant.PRODUCT_CREATED, groupId = AppConstant.GROUP_ID)
    public void consume(ProductResponse productResponse){
        System.out.println(productResponse.toString());
    }
}
