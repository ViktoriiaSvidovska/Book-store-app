package book.store.app.bookstoreapp.dto.shoppingcart;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class AddCartItemRequestDto {
    @NotBlank
    @Positive
    private Long bookId;
    @NotBlank(message = "Quantity cannot be empty")
    @Positive
    private Integer quantity;
}
