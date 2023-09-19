package book.store.app.bookstoreapp.mapper;

import book.store.app.bookstoreapp.config.MapperConfig;
import book.store.app.bookstoreapp.dto.shoppingcart.AddCartItemRequestDto;
import book.store.app.bookstoreapp.dto.shoppingcart.CartItemResponseDto;
import book.store.app.bookstoreapp.model.Book;
import book.store.app.bookstoreapp.model.CartItem;
import book.store.app.bookstoreapp.model.ShoppingCart;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface CartItemMapper {
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "id", ignore = true)
    CartItem toModel(AddCartItemRequestDto requestDto, Book book,
                     ShoppingCart shoppingCart);

    @Mapping(target = "bookId", source = "cartItem.book.id")
    @Mapping(target = "bookTitle", source = "cartItem.book.title")
    CartItemResponseDto toDto(CartItem cartItem);
}
