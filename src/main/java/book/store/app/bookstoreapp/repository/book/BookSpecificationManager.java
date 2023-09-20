package book.store.app.bookstoreapp.repository.book;

import book.store.app.bookstoreapp.model.Book;
import book.store.app.bookstoreapp.repository.SpecificationManager;
import book.store.app.bookstoreapp.repository.SpecificationProvider;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class BookSpecificationManager implements SpecificationManager<Book> {
    private final Map<String, Supplier<SpecificationProvider<Book>>> providersMap;

    public BookSpecificationManager(List<Supplier<SpecificationProvider<Book>>> suppliers) {
        providersMap = suppliers.stream()
                .collect(Collectors.toMap(
                        supplier -> supplier.get().getKey(),
                        Function.identity()
                ));
    }

    @Override
    public Specification<Book> get(String filterKey, String[] params) {
        if (!providersMap.containsKey(filterKey)) {
            throw new RuntimeException("Key " + filterKey + " is not supported");
        }
        return providersMap.get(filterKey).get().getSpecification("like", params);
    }
}
