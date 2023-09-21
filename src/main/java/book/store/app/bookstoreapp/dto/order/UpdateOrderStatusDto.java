package book.store.app.bookstoreapp.dto.order;

import book.store.app.bookstoreapp.model.OrderStatus;
import jakarta.validation.constraints.NotBlank;

public record UpdateOrderStatusDto(@NotBlank OrderStatus status) {
}
