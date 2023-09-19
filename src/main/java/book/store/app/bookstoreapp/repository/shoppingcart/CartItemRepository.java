package book.store.app.bookstoreapp.repository.shoppingcart;

import book.store.app.bookstoreapp.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
