package book.store.app.bookstoreapp.service;

import book.store.app.bookstoreapp.dto.book.BookDtoNoCategory;
import book.store.app.bookstoreapp.dto.category.CategoryResponseDto;
import book.store.app.bookstoreapp.dto.category.CreateCategoryRequestDto;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface CategoryService {
    List<CategoryResponseDto> findAll(Pageable pageable);

    CategoryResponseDto getById(Long id);

    CategoryResponseDto save(CreateCategoryRequestDto categoryDto);

    CategoryResponseDto update(Long id, CreateCategoryRequestDto categoryDto);

    void deleteById(Long id);

    List<BookDtoNoCategory> getBooksByCategoriesId(Long id);
}
