package book.store.app.bookstoreapp.service.impl;

import book.store.app.bookstoreapp.dto.BookResponseDto;
import book.store.app.bookstoreapp.dto.CreateBookRequestDto;
import book.store.app.bookstoreapp.exception.EntityNotFoundException;
import book.store.app.bookstoreapp.mapper.BookMapper;
import book.store.app.bookstoreapp.model.Book;
import book.store.app.bookstoreapp.repository.BookRepository;
import book.store.app.bookstoreapp.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public BookServiceImpl(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    @Override
    public BookResponseDto save(CreateBookRequestDto product) {
        return bookMapper.toDto(bookRepository.save(bookMapper.toModel(product)));
    }

    @Override
    public List<BookResponseDto> findAll() {
        return bookRepository.findAll()
                .stream()
                .map(bookMapper::toDto)
                .toList();
    }

    @Override
    public BookResponseDto findById(Long id) {
        return bookMapper.toDto(bookRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Can't find book by id: " + id)));
    }
}
