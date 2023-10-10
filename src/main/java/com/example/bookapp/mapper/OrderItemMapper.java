package com.example.bookapp.mapper;

import com.example.bookapp.config.MapperConfig;
import com.example.bookapp.dto.order.OrderItemResponseDto;
import com.example.bookapp.model.Book;
import com.example.bookapp.model.CartItem;
import com.example.bookapp.model.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface OrderItemMapper {
    @Mapping(target = "bookId", source = "orderItem.book.id")
    OrderItemResponseDto toDto(OrderItem orderItem);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "price", source = "book.price")
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "order", ignore = true)
    OrderItem cartItemToOrderItem(CartItem cartItem, Book book);
}
