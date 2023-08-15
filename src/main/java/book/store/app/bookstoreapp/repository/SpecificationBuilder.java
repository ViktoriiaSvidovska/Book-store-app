package book.store.app.bookstoreapp.repository;

import book.store.app.bookstoreapp.dto.BookSearchParametersDto;
import org.springframework.data.jpa.domain.Specification;

public interface SpecificationBuilder<T> {
    Specification<T> build(BookSearchParametersDto bookSearchParametersDto);
}
