package book.store.app.bookstoreapp.dto.category;

import book.store.app.bookstoreapp.model.Book;
import lombok.Data;

import java.util.Set;

@Data
public class CategoryResponseDto {
    private Long id;
    private String name;
    private String description;
    private Set<Book> books;
}
