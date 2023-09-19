package book.store.app.bookstoreapp.service.impl;

import book.store.app.bookstoreapp.dto.shoppingcart.AddCartItemRequestDto;
import book.store.app.bookstoreapp.exception.EntityNotFoundException;
import book.store.app.bookstoreapp.mapper.CartItemMapper;
import book.store.app.bookstoreapp.model.Book;
import book.store.app.bookstoreapp.model.CartItem;
import book.store.app.bookstoreapp.model.ShoppingCart;
import book.store.app.bookstoreapp.model.User;
import book.store.app.bookstoreapp.repository.book.BookRepository;
import book.store.app.bookstoreapp.repository.shoppingcart.CartItemRepository;
import book.store.app.bookstoreapp.repository.shoppingcart.ShoppingCartRepository;
import book.store.app.bookstoreapp.service.CartItemService;
import book.store.app.bookstoreapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemService {
    private final CartItemRepository cartItemRepository;
    private final CartItemMapper cartItemMapper;
    private final BookRepository bookRepository;
    private final UserService userService;
    private final ShoppingCartRepository shoppingCartRepository;

    @Override
    public CartItem save(AddCartItemRequestDto requestDto) {
        Book book = bookRepository.findById(requestDto.getBookId())
                    .orElseThrow(() ->
                    new EntityNotFoundException("Can't find book by id: "
                    + requestDto.getBookId()));
        return cartItemRepository.save(cartItemMapper.toModel(requestDto, book,
                getShoppingCartModel()));
    }

    @Override
    public CartItem getById(Long id) {
        return cartItemRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Can't find cart item by id: " + id));
    }

    @Override
    public void delete(Long cartItemId) {
        if (!cartItemRepository.existsById(cartItemId)) {
            throw new EntityNotFoundException("Can't delete cart item by id: "
                    + cartItemId);
        }
        cartItemRepository.deleteById(cartItemId);
    }

    private ShoppingCart getShoppingCartModel() {
        User user = userService.getUser();
        return shoppingCartRepository.findById(user.getId()).get();
    }
}
