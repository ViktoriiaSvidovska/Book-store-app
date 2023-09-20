package book.store.app.bookstoreapp.dto.book;

import jakarta.validation.constraints.Positive;

public record UpdateBookQuantity(@Positive int quantity){
}
