package book.store.app.bookstoreapp.controller;

import book.store.app.bookstoreapp.dto.BookResponseDto;
import book.store.app.bookstoreapp.dto.BookSearchParametersDto;
import book.store.app.bookstoreapp.dto.CreateBookRequestDto;
import java.util.List;
import book.store.app.bookstoreapp.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<BookResponseDto> getAllBooks() {
        return bookService.findAll();
    }

    @GetMapping("/{id}")
    public BookResponseDto findById(@PathVariable Long id) {
        return bookService.findById(id);
    }

    @PostMapping
    public BookResponseDto createBook(@RequestBody CreateBookRequestDto requestDto) {
        return bookService.save(requestDto);
    }

    @PutMapping("/{id}")
    public BookResponseDto updateBook(@PathVariable Long id,
                                      @RequestBody CreateBookRequestDto requestDto) {
        return bookService.update(id, requestDto);
    }

    @GetMapping("/search")
    public List<BookResponseDto> search(BookSearchParametersDto bookSearchParameters) {
        return bookService.search(bookSearchParameters);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable Long id) {
        bookService.delete(id);
    }
}
