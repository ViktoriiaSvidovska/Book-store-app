package book.store.app.bookstoreapp.book;

import book.store.app.bookstoreapp.model.Book;
import book.store.app.bookstoreapp.repository.SpecificationProvider;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class TitleSpecificationProvider implements SpecificationProvider<Book> {
        public static final String TITLE = "title";

        @Override
        public String getKey() {
        return TITLE;
    }

        public Specification<Book> getSpecification(String[] params) {
        return ((root, query, criteriaBuilder) ->
                root.get(TITLE)
                    .in(Arrays.stream(params)
                    .toArray()));
    }
}
