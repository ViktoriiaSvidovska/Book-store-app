package com.example.bookapp.dto.order;

import com.example.bookapp.model.Status;
import jakarta.validation.constraints.NotBlank;

public record UpdateOrderStatusDto(@NotBlank Status status) {
}
