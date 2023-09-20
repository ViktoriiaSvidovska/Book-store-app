package book.store.app.bookstoreapp.service.impl;

import book.store.app.bookstoreapp.dto.book.UpdateBookQuantity;
import book.store.app.bookstoreapp.dto.shoppingcart.AddCartItemRequestDto;
import book.store.app.bookstoreapp.dto.shoppingcart.ShoppingCartResponseDto;
import book.store.app.bookstoreapp.mapper.ShoppingCartMapper;
import book.store.app.bookstoreapp.model.CartItem;
import book.store.app.bookstoreapp.model.ShoppingCart;
import book.store.app.bookstoreapp.model.User;
import book.store.app.bookstoreapp.repository.shoppingcart.ShoppingCartRepository;
import book.store.app.bookstoreapp.service.CartItemService;
import book.store.app.bookstoreapp.service.ShoppingCartService;
import book.store.app.bookstoreapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final CartItemService cartItemService;
    private final UserService userService;
    private final ShoppingCartRepository shoppingCartRepository;
    private final ShoppingCartMapper shoppingCartMapper;

    @Override
    public ShoppingCartResponseDto getShoppingCart() {
        return shoppingCartMapper.toDto(getShoppingCartModel());
    }

    @Override
    public ShoppingCartResponseDto addCartItem(AddCartItemRequestDto requestDto) {
        ShoppingCart cart = getShoppingCartModel();
        CartItem cartItem = cartItemService.save(requestDto);
        cart.addCartItem(cartItem);
        return getShoppingCart();
    }

    @Transactional
    @Override
    public ShoppingCartResponseDto updateQuantityOfBooks(Long cartItemId,
                                                         UpdateBookQuantity requestDto) {
        cartItemService.getById(cartItemId).setQuantity(requestDto.quantity());
        return getShoppingCart();
    }

    @Override
    public ShoppingCartResponseDto deleteCartItem(Long cartItemId) {
        cartItemService.delete(cartItemId);
        return getShoppingCart();
    }

    @Override
    public ShoppingCart getShoppingCartModel() {
        User user = userService.getUser();
        return shoppingCartRepository.findById(user.getId()).get();
    }
}
