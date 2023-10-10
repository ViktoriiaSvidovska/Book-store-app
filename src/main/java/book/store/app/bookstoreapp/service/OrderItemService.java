package book.store.app.bookstoreapp.service;

import book.store.app.bookstoreapp.dto.order.OrderItemResponseDto;
import book.store.app.bookstoreapp.model.OrderItem;

public interface OrderItemService {
    OrderItemResponseDto findOrderItemByOrderIdAndId(Long orderId,
            Long itemId);

    OrderItem save(OrderItem orderItem);
}
