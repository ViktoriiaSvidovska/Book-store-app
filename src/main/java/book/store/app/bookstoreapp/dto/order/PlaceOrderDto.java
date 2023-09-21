package book.store.app.bookstoreapp.dto.order;

import jakarta.validation.constraints.NotBlank;

public record PlaceOrderDto(@NotBlank String shippingAddress) {
}
