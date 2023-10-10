package com.example.bookapp.dto.order;

import jakarta.validation.constraints.NotBlank;

public record PlaceOrderDto(@NotBlank String shippingAddress) {
}
