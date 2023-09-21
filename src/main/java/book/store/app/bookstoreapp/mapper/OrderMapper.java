package book.store.app.bookstoreapp.mapper;

import book.store.app.bookstoreapp.config.MapperConfig;
import book.store.app.bookstoreapp.dto.order.OrderResponseDto;
import book.store.app.bookstoreapp.model.Book;
import book.store.app.bookstoreapp.model.CartItem;
import book.store.app.bookstoreapp.model.Order;
import book.store.app.bookstoreapp.model.ShoppingCart;
import java.math.BigDecimal;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class, uses = OrderItemMapper.class)
public interface OrderMapper {

    @Mapping(target = "orderItems", source = "orderItems")
    @Mapping(target = "userId", source = "order.user.id")
    OrderResponseDto toDto(Order order);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "total", ignore = true)
    @Mapping(target = "status", expression
            = "java(book.store.app.bookstoreapp.model.OrderStatus.PENDING)")
    @Mapping(target = "orderDate", expression
            = "java(java.time.LocalDateTime.now())")
    Order toOrderFromCart(ShoppingCart shoppingCart);

    @AfterMapping
    default void setOrderTotal(@MappingTarget Order order,
                               ShoppingCart shoppingCart) {
        order.setTotal(getTotal(shoppingCart));
    }

    private BigDecimal getTotal(ShoppingCart shoppingCart) {
        return shoppingCart.getCartItems()
                .stream()
                .map(CartItem::getBook)
                .map(Book::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
