package book.store.app.bookstoreapp.dto.shoppingcart;

import java.util.Set;
import lombok.Data;

@Data
public class ShoppingCartResponseDto {
    private Long userId;
    private Set<CartItemResponseDto> cartItems;
}
