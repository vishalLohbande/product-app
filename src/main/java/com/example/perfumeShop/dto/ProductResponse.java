package com.example.perfumeShop.dto;

public record ProductResponse(
        long id,
        String name,
        double price,
        String message
) {
}
