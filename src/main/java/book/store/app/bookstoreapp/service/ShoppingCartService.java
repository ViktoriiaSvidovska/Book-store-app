package book.store.app.bookstoreapp.service;

import book.store.app.bookstoreapp.dto.book.UpdateBookQuantity;
import book.store.app.bookstoreapp.dto.shoppingcart.AddCartItemRequestDto;
import book.store.app.bookstoreapp.dto.shoppingcart.ShoppingCartResponseDto;
import book.store.app.bookstoreapp.model.ShoppingCart;

public interface ShoppingCartService {
    ShoppingCartResponseDto getShoppingCart();

    ShoppingCartResponseDto addCartItem(AddCartItemRequestDto requestDto);

    ShoppingCartResponseDto updateQuantityOfBooks(Long cartItemId,
                                                  UpdateBookQuantity quantity);

    ShoppingCartResponseDto deleteCartItem(Long cartItemId);

    ShoppingCart getShoppingCartModel();
}
