package book.store.app.bookstoreapp.service.impl;

import book.store.app.bookstoreapp.dto.order.OrderItemResponseDto;
import book.store.app.bookstoreapp.dto.order.OrderResponseDto;
import book.store.app.bookstoreapp.dto.order.PlaceOrderDto;
import book.store.app.bookstoreapp.dto.order.UpdateOrderStatusDto;
import book.store.app.bookstoreapp.exception.EntityNotFoundException;
import book.store.app.bookstoreapp.mapper.OrderItemMapper;
import book.store.app.bookstoreapp.mapper.OrderMapper;
import book.store.app.bookstoreapp.model.Book;
import book.store.app.bookstoreapp.model.CartItem;
import book.store.app.bookstoreapp.model.Order;
import book.store.app.bookstoreapp.model.OrderItem;
import book.store.app.bookstoreapp.model.ShoppingCart;
import book.store.app.bookstoreapp.repository.book.BookRepository;
import book.store.app.bookstoreapp.repository.order.OrderRepository;
import book.store.app.bookstoreapp.repository.shoppingcart.ShoppingCartRepository;
import book.store.app.bookstoreapp.service.OrderItemService;
import book.store.app.bookstoreapp.service.OrderService;
import book.store.app.bookstoreapp.service.ShoppingCartService;
import book.store.app.bookstoreapp.service.UserService;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

    @Override
    public OrderResponseDto placeOrder(PlaceOrderDto placeOrderDto) {
        ShoppingCart shoppingCart = shoppingCartService
                .getShoppingCartModel();

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
    public void updateOrderStatus(Long orderId,
                UpdateOrderStatusDto updateOrderStatusDto) {
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
        shoppingCartRepository.delete(shoppingCart);
        ShoppingCart shoppingCartNew = new ShoppingCart();
        shoppingCartNew.setUser(shoppingCart.getUser());
        shoppingCartRepository.save(shoppingCartNew);
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
                new EntityNotFoundException("Can't find order by id: "
                        + orderId));
    }

    private Set<OrderItem> getSavedOrderItems(Set<OrderItem> orderItems) {
        return orderItems
                .stream()
                .map(orderItemService::save)
                .collect(Collectors.toSet());
    }

    private Book getBookFromCartItem(CartItem cartItem) {
        return bookRepository.findById(cartItem.getBook().getId()).orElseThrow(() ->
                new EntityNotFoundException("Can't find book by id: "
                        + cartItem.getBook().getId()));
    }
}
