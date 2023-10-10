package com.example.bookapp.service;

import com.example.bookapp.dto.cart.AddCartItemRequestDto;
import com.example.bookapp.dto.cart.ShoppingCartResponseDto;
import com.example.bookapp.dto.cart.UpdateBookQuantityInCartDto;
import com.example.bookapp.model.ShoppingCart;

public interface ShoppingCartService {
    ShoppingCartResponseDto getShoppingCart();

    ShoppingCartResponseDto addCartItem(AddCartItemRequestDto requestDto);

    ShoppingCartResponseDto updateQuantityOfBooks(Long cartItemId,
                                                  UpdateBookQuantityInCartDto quantity);

    ShoppingCartResponseDto deleteCartItem(Long cartItemId);

    ShoppingCart getShoppingCartModel();

}
