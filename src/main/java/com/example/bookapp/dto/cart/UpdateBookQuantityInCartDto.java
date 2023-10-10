package com.example.bookapp.dto.cart;

import jakarta.validation.constraints.Positive;

public record UpdateBookQuantityInCartDto(@Positive int quantity) {
}
