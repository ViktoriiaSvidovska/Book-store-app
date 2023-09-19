package book.store.app.bookstoreapp.mapper;

import book.store.app.bookstoreapp.config.MapperConfig;
import book.store.app.bookstoreapp.dto.shoppingcart.ShoppingCartResponseDto;
import book.store.app.bookstoreapp.model.ShoppingCart;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class, uses = CartItemMapper.class)
public interface ShoppingCartMapper {
    @Mapping(target = "cartItems", source = "cartItems")
    @Mapping(target = "userId", source = "user.id")
    ShoppingCartResponseDto toDto(ShoppingCart shoppingCart);
}
