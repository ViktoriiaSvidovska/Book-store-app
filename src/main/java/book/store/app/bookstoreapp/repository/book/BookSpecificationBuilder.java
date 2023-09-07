package book.store.app.bookstoreapp.repository.book;

import book.store.app.bookstoreapp.dto.book.BookSearchParametersDto;
import book.store.app.bookstoreapp.model.Book;
import book.store.app.bookstoreapp.repository.SpecificationBuilder;
import book.store.app.bookstoreapp.repository.SpecificationProviderManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BookSpecificationBuilder implements SpecificationBuilder<Book> {
    public static final String AUTHOR = "author";
    public static final String TITLE = "title";
    private final SpecificationProviderManager<Book> bookSpecificationProviderManager;

    @Override
    public Specification<Book> build(BookSearchParametersDto bookSearchParametersDto) {
        Specification<Book> specification = Specification.where(null);
        if (bookSearchParametersDto.authors() != null
                && bookSearchParametersDto.authors().length > 0) {
            specification = specification.and(bookSpecificationProviderManager
                    .getSpecificationProvider(AUTHOR)
                    .getSpecification(bookSearchParametersDto.authors()));
        }
        if (bookSearchParametersDto.titles() != null
                && bookSearchParametersDto.titles().length > 0) {
            specification = specification.and(bookSpecificationProviderManager
                    .getSpecificationProvider(TITLE)
                    .getSpecification(bookSearchParametersDto.titles()));
        }
        return specification;
    }
}
