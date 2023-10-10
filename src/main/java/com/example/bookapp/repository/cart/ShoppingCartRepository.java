package com.example.bookapp.repository.cart;

import com.example.bookapp.model.ShoppingCart;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    @EntityGraph(attributePaths = "cartItems")
    Optional<ShoppingCart> findById(Long id);
}
