package com.example.bookapp.service.impl;

import com.example.bookapp.dto.cart.AddCartItemRequestDto;
import com.example.bookapp.dto.cart.ShoppingCartResponseDto;
import com.example.bookapp.dto.cart.UpdateBookQuantityInCartDto;
import com.example.bookapp.exception.EntityNotFoundException;
import com.example.bookapp.mapper.CartItemMapper;
import com.example.bookapp.mapper.ShoppingCartMapper;
import com.example.bookapp.model.Book;
import com.example.bookapp.model.CartItem;
import com.example.bookapp.model.ShoppingCart;
import com.example.bookapp.model.User;
import com.example.bookapp.repository.book.BookRepository;
import com.example.bookapp.repository.cart.CartItemRepository;
import com.example.bookapp.repository.cart.ShoppingCartRepository;
import com.example.bookapp.service.ShoppingCartService;
import com.example.bookapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final CartItemRepository cartItemRepository;
    private final UserService userService;
    private final CartItemMapper cartItemMapper;
    private final BookRepository bookRepository;
    private final ShoppingCartRepository shoppingCartRepository;
    private final ShoppingCartMapper shoppingCartMapper;

    @Override
    public ShoppingCartResponseDto getShoppingCart() {
        return shoppingCartMapper.toDto(getShoppingCartModel());
    }

    @Override
    public ShoppingCartResponseDto addCartItem(AddCartItemRequestDto requestDto) {
        ShoppingCart cart = getShoppingCartModel();
        Book book = bookRepository.findById(requestDto.getBookId()).orElseThrow(() ->
                new EntityNotFoundException("can't find book by id: " + requestDto.getBookId()));
        CartItem cartItem = cartItemMapper.toModel(requestDto, book);
        cartItem.setShoppingCart(cart);
        CartItem savedCartItem = cartItemRepository.save(cartItem);
        cart.addCartItem(savedCartItem);
        return getShoppingCart();
    }

    @Transactional
    @Override
    public ShoppingCartResponseDto updateQuantityOfBooks(Long cartItemId,
                                                         UpdateBookQuantityInCartDto requestDto) {
        cartItemRepository.findById(cartItemId).orElseThrow(() ->
                new EntityNotFoundException("can't find cart item by id: " + cartItemId))
                .setQuantity(requestDto.quantity());
        return getShoppingCart();
    }

    @Override
    public ShoppingCartResponseDto deleteCartItem(Long cartItemId) {
        if (!cartItemRepository.existsById(cartItemId)) {
            throw new EntityNotFoundException("can't delete cart item by id: " + cartItemId);
        }
        cartItemRepository.deleteById(cartItemId);
        return getShoppingCart();
    }

    @Override
    public ShoppingCart getShoppingCartModel() {
        User user = userService.getUser();
        return shoppingCartRepository.findById(user.getId()).get();
    }
}
