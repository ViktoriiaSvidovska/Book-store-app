package book.store.app.bookstoreapp.repository.book;

import book.store.app.bookstoreapp.model.Book;
import book.store.app.bookstoreapp.repository.SpecificationProvider;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class AuthorSpecificationProvider implements SpecificationProvider<Book> {
    private static final String FIELD_NAME = "author";

    @Override
    public String getKey() {
        return "like";
    }

    public Specification<Book> getSpecification(String operation, String[] params) {
        if ("like".equals(operation)) {
            return (root, query, criteriaBuilder) ->
                    criteriaBuilder.like(root.get(FIELD_NAME), "%" + params[0] + "%");
        } else {
            throw new UnsupportedOperationException("Unsupported operation: " + operation);
        }
    }
}
