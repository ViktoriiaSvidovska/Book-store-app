package com.example.bookapp.service.impl;

import com.example.bookapp.dto.order.OrderItemResponseDto;
import com.example.bookapp.dto.order.OrderResponseDto;
import com.example.bookapp.dto.order.PlaceOrderDto;
import com.example.bookapp.dto.order.UpdateOrderStatusDto;
import com.example.bookapp.exception.EntityNotFoundException;
import com.example.bookapp.mapper.OrderItemMapper;
import com.example.bookapp.mapper.OrderMapper;
import com.example.bookapp.model.Book;
import com.example.bookapp.model.CartItem;
import com.example.bookapp.model.Order;
import com.example.bookapp.model.OrderItem;
import com.example.bookapp.model.ShoppingCart;
import com.example.bookapp.repository.book.BookRepository;
import com.example.bookapp.repository.cart.CartItemRepository;
import com.example.bookapp.repository.cart.ShoppingCartRepository;
import com.example.bookapp.repository.order.OrderRepository;
import com.example.bookapp.service.OrderItemService;
import com.example.bookapp.service.OrderService;
import com.example.bookapp.service.ShoppingCartService;
import com.example.bookapp.service.UserService;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final UserService userService;
    private final OrderItemMapper orderItemMapper;
    private final OrderMapper orderMapper;
    private final OrderItemService orderItemService;
    private final ShoppingCartService shoppingCartService;
    private final BookRepository bookRepository;
    private final ShoppingCartRepository shoppingCartRepository;
    private final CartItemRepository cartItemRepository;

    @Override
    @Transactional
    public OrderResponseDto placeOrder(PlaceOrderDto placeOrderDto) {
        ShoppingCart shoppingCart = shoppingCartService.getShoppingCartModel();

        Order order = orderMapper.toOrderFromCart(shoppingCart);
        order.setShippingAddress(placeOrderDto.shippingAddress());
        Order savedOrder = orderRepository.save(order);

        Set<OrderItem> orderItems = getOrderItemsFromCart(shoppingCart);
        orderItems.forEach(orderItem -> orderItem.setOrder(savedOrder));
        savedOrder.setOrderItems(getSavedOrderItems(orderItems));
        completePurchase(shoppingCart);
        return orderMapper.toDto(savedOrder);
    }

    @Override
    public void updateOrderStatus(Long orderId, UpdateOrderStatusDto updateOrderStatusDto) {
        Order orderById = getOrderById(orderId);
        orderById.setStatus(updateOrderStatusDto.status());
        orderRepository.save(orderById);
    }

    @Override
    public List<OrderResponseDto> getOrderHistory(Pageable pageable) {
        return orderRepository.getAllByUser(pageable, userService.getUser())
                .stream()
                .map(orderMapper::toDto)
                .toList();
    }

    @Override
    public List<OrderItemResponseDto> getOrderItems(Long orderId) {
        return getOrderById(orderId)
                .getOrderItems()
                .stream()
                .map(orderItemMapper::toDto)
                .toList();
    }

    @Override
    public OrderItemResponseDto getOrderItem(Long orderId, Long itemId) {
        return orderItemService.findOrderItemByOrderIdAndId(orderId, itemId);
    }

    @Override
    public void completePurchase(ShoppingCart shoppingCart) {
        //        shoppingCartRepository.delete(shoppingCart);
        //        ShoppingCart shoppingCartNew = new ShoppingCart();
        //        shoppingCartNew.setUser(shoppingCart.getUser());
        //        shoppingCartRepository.save(shoppingCartNew);

        shoppingCart.setCartItems(new HashSet<>());
        cartItemRepository.deleteCartItemByShoppingCartId(shoppingCart.getId());
    }

    private Set<OrderItem> getOrderItemsFromCart(ShoppingCart shoppingCart) {
        return shoppingCart.getCartItems()
                .stream()
                .map(cartItem -> orderItemMapper.cartItemToOrderItem(cartItem,
                        getBookFromCartItem(cartItem)))
                .collect(Collectors.toSet());
    }

    private Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId).orElseThrow(() ->
                new EntityNotFoundException("can't find order by id: " + orderId));
    }

    private Set<OrderItem> getSavedOrderItems(Set<OrderItem> orderItems) {
        return orderItems
                .stream()
                .map(orderItemService::save)
                .collect(Collectors.toSet());
    }

    private Book getBookFromCartItem(CartItem cartItem) {
        return bookRepository.findById(cartItem.getBook().getId()).orElseThrow(() ->
                new EntityNotFoundException("can't find book by id: "
                        + cartItem.getBook().getId()));
    }
}
