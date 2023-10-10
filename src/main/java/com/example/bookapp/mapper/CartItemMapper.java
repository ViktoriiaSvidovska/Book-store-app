package com.example.bookapp.mapper;

import com.example.bookapp.config.MapperConfig;
import com.example.bookapp.dto.cart.AddCartItemRequestDto;
import com.example.bookapp.dto.cart.CartItemResponseDto;
import com.example.bookapp.model.Book;
import com.example.bookapp.model.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface CartItemMapper {
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "id", ignore = true)
    CartItem toModel(AddCartItemRequestDto requestDto, Book book);

    @Mapping(target = "bookId", source = "cartItem.book.id")
    @Mapping(target = "bookTitle", source = "cartItem.book.title")
    CartItemResponseDto toDto(CartItem cartItem);
}
