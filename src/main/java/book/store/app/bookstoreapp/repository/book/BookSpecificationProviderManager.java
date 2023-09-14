package book.store.app.bookstoreapp.repository.book;

import book.store.app.bookstoreapp.model.Book;
import book.store.app.bookstoreapp.repository.SpecificationProvider;
import book.store.app.bookstoreapp.repository.SpecificationProviderManager;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookSpecificationProviderManager implements SpecificationProviderManager<Book> {
    private final List<SpecificationProvider<Book>> bookSpecificationProviders;

    @Override
    public SpecificationProvider<Book> getSpecificationProvider(String key) {
        return bookSpecificationProviders
                .stream()
                .filter(p -> p.getKey().equals(key))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Can't find specification "
                        + "provider for key parameter: " + key));
    }
}
