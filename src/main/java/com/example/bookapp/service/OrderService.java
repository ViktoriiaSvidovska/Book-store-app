package com.example.bookapp.service;

import com.example.bookapp.dto.order.OrderItemResponseDto;
import com.example.bookapp.dto.order.OrderResponseDto;
import com.example.bookapp.dto.order.PlaceOrderDto;
import com.example.bookapp.dto.order.UpdateOrderStatusDto;
import com.example.bookapp.model.ShoppingCart;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface OrderService {
    OrderResponseDto placeOrder(PlaceOrderDto placeOrderDto);

    void updateOrderStatus(Long orderId, UpdateOrderStatusDto updateOrderStatusDto);

    List<OrderResponseDto> getOrderHistory(Pageable pageable);

    List<OrderItemResponseDto> getOrderItems(Long orderId);

    OrderItemResponseDto getOrderItem(Long orderId, Long itemId);

    void completePurchase(ShoppingCart shoppingCart);
}
