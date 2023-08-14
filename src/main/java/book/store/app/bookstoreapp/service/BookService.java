package book.store.app.bookstoreapp.service;

import book.store.app.bookstoreapp.dto.BookResponseDto;
import book.store.app.bookstoreapp.dto.CreateBookRequestDto;
import book.store.app.bookstoreapp.model.Book;
import java.util.List;

public interface BookService {
    BookResponseDto save(CreateBookRequestDto product);
    List<BookResponseDto> findAll();
    BookResponseDto findById(Long id);
}
