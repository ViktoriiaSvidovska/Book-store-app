package book.store.app.bookstoreapp.service;

import book.store.app.bookstoreapp.dto.shoppingcart.AddCartItemRequestDto;
import book.store.app.bookstoreapp.model.CartItem;

public interface CartItemService {
    CartItem save(AddCartItemRequestDto requestDto);

    CartItem getById(Long id);

    void delete(Long cartItemId);
}
