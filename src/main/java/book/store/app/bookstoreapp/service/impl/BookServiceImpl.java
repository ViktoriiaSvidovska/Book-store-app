package book.store.app.bookstoreapp.service.impl;

import book.store.app.bookstoreapp.model.Book;
import book.store.app.bookstoreapp.repository.BookRepository;
import book.store.app.bookstoreapp.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book save(Book product) {
        return bookRepository.save(product);
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }
}
