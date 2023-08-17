package book.store.app.bookstoreapp.controller;

import book.store.app.bookstoreapp.dto.BookResponseDto;
import book.store.app.bookstoreapp.dto.BookSearchParametersDto;
import book.store.app.bookstoreapp.dto.CreateBookRequestDto;
import org.springframework.data.domain.Pageable;
import java.util.List;
import book.store.app.bookstoreapp.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/books")
@Tag(name = "Books", description = "Managing books")
public class BookController {
    private final BookService bookService;

    @GetMapping
    @Operation(
            summary = "Get all books",
            description = "Get a list of all available books in the library's collection"
    )
    public List<BookResponseDto> getAllBooks(Pageable pageable) {
        return bookService.findAll(pageable);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find book by Id",
            description = "Obtain information about a book using its unique identifier")
    public BookResponseDto findById(@PathVariable Long id) {
        return bookService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new book",
            description = "Add a new book to the library's collection with the provided details")
    public BookResponseDto createBook(@RequestBody @Valid CreateBookRequestDto requestDto) {
        return bookService.save(requestDto);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Update book",
            description = "Update information about the specific book in the library's collection")
    public BookResponseDto updateBook(@PathVariable Long id,
                                      @RequestBody @Valid CreateBookRequestDto requestDto) {
        return bookService.update(id, requestDto);
    }

    @GetMapping("/search")
    @Operation( summary = "Search for the book",
            description = "Search for book in the library's collection based on the specified criteria" )
    public List<BookResponseDto> search(BookSearchParametersDto bookSearchParameters) {
        return bookService.search(bookSearchParameters);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete book",
            description = "Remove a specific book from the library's collection")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable Long id) {
        bookService.delete(id);
    }
}
