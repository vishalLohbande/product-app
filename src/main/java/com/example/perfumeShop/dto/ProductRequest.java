package com.example.perfumeShop.dto;


public record ProductRequest(
        Long id,
        String name,
        Double price
) {
}
