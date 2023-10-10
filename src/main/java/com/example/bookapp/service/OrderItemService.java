package com.example.bookapp.service;

import com.example.bookapp.dto.order.OrderItemResponseDto;
import com.example.bookapp.model.OrderItem;

public interface OrderItemService {
    OrderItemResponseDto findOrderItemByOrderIdAndId(Long orderId, Long itemId);

    OrderItem save(OrderItem orderItem);
}
