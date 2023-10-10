package com.example.bookapp.mapper;

import com.example.bookapp.config.MapperConfig;
import com.example.bookapp.dto.cart.ShoppingCartResponseDto;
import com.example.bookapp.model.ShoppingCart;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class, uses = CartItemMapper.class)
public interface ShoppingCartMapper {
    @Mapping(target = "cartItems", source = "cartItems")
    @Mapping(target = "userId", source = "user.id")
    ShoppingCartResponseDto toDto(ShoppingCart shoppingCart);
}
