package book.store.app.bookstoreapp.dto.category;

import book.store.app.bookstoreapp.model.Book;
import java.util.Set;
import lombok.Data;

@Data
public class CategoryResponseDto {
    private Long id;
    private String name;
    private String description;
    private Set<Book> books;
}
