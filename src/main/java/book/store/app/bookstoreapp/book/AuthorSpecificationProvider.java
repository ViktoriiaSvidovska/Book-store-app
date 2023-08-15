package book.store.app.bookstoreapp.book;

import book.store.app.bookstoreapp.model.Book;
import book.store.app.bookstoreapp.repository.SpecificationProvider;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import java.util.Arrays;

@Component
public class AuthorSpecificationProvider implements SpecificationProvider<Book> {
    public static final String AUTHOR = "author";

    @Override
    public String getKey() {
        return AUTHOR;
    }

    public Specification<Book> getSpecification(String[] params) {
        return ((root, query, criteriaBuilder) ->
                root.get(AUTHOR)
                    .in(Arrays.stream(params)
                    .toArray()));
    }
}
