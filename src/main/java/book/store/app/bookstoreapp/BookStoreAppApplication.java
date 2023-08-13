package book.store.app.bookstoreapp;

import book.store.app.bookstoreapp.model.Book;
import book.store.app.bookstoreapp.service.BookService;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BookStoreAppApplication {
    @Autowired
    private BookService bookService;

    public static void main(String[] args) {
        SpringApplication.run(BookStoreAppApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            Book book = new Book();
            book.setTitle("Title");
            book.setAuthor("Author");
            book.setIsbn("Isbn");
            book.setPrice(BigDecimal.valueOf(100));
            book.setDescription("Description");
            book.setCoverImage("CoverImage");
            bookService.save(book);
            System.out.println(bookService.findAll());
        };
    }
}
