package book.store.app.bookstoreapp.service;

import book.store.app.bookstoreapp.dto.order.OrderItemResponseDto;
import book.store.app.bookstoreapp.dto.order.OrderResponseDto;
import book.store.app.bookstoreapp.dto.order.PlaceOrderDto;
import book.store.app.bookstoreapp.dto.order.UpdateOrderStatusDto;
import book.store.app.bookstoreapp.model.ShoppingCart;
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
