package book.store.app.bookstoreapp.controller;

import book.store.app.bookstoreapp.dto.book.BookResponseDto;
import book.store.app.bookstoreapp.dto.book.CreateBookRequestDto;
import book.store.app.bookstoreapp.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/books")
@Tag(name = "Books", description = "Managing books")
public class BookController {
    private final BookService bookService;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    @Operation(summary = "Get all books",
            description = "Get a list of all available books in the library's collection")
    public List<BookResponseDto> getAllBooks(Pageable pageable) {
        return bookService.findAll(pageable);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    @Operation(summary = "Find book by Id",
            description = "Obtain information about a book using its unique identifier")
    public BookResponseDto findById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "Create a new book. <ONLY FOR ADMIN ROLE>",
            description = "Add a new book to the library's collection with the provided details")
    @ApiResponse(responseCode = "201",
            description = "Book created",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = BookResponseDto.class))})
    public BookResponseDto createBook(@RequestBody @Valid CreateBookRequestDto requestDto) {
        return bookService.save(requestDto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "Update book. <ONLY FOR ADMIN ROLE>",
            description = "Update information about the specific book in the library's collection")
    @ApiResponse(responseCode = "200",
            description = "Book updated",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = BookResponseDto.class))})
    public BookResponseDto updateBook(@PathVariable Long id,
                                      @RequestBody @Valid CreateBookRequestDto requestDto) {
        return bookService.update(id, requestDto);
    }

    @GetMapping("/search")
    @PreAuthorize("hasRole('ROLE_USER')")
    @Operation(summary = "Search for the book", description
                    = "Search for book in the library's collection based on the specified criteria")
    public List<BookResponseDto> search(@RequestParam Map<String, String> searchParameters,
                                        @ParameterObject Pageable pageable) {
        return bookService.search(searchParameters, pageable);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "Delete book. <ONLY FOR ADMIN ROLE>",
            description = "Remove a specific book from the library's collection")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiResponse(responseCode = "204",
            description = "Book deleted")
    public void deleteBook(@PathVariable Long id) {
        bookService.delete(id);
    }
}
