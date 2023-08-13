package book.store.app.bookstoreapp.service;

import book.store.app.bookstoreapp.model.Book;
import java.util.List;

public interface BookService {
    Book save(Book product);

    List<Book> findAll();
}
