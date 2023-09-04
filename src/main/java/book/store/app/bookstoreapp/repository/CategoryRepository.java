package book.store.app.bookstoreapp.repository;

import book.store.app.bookstoreapp.model.Book;
import book.store.app.bookstoreapp.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query("SELECT b FROM Book b JOIN b.categories c WHERE c.id = :id")
    List<Book> getBooksByCategoriesId(Long id);
}
