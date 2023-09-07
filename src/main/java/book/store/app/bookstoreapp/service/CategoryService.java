package book.store.app.bookstoreapp.service;

import book.store.app.bookstoreapp.dto.book.BookDtoWithoutCategoryIds;
import book.store.app.bookstoreapp.dto.category.CategoryResponseDto;
import book.store.app.bookstoreapp.dto.category.CreateCategoryRequestDto;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface CategoryService {
    List findAll(Pageable pageable);

    CategoryResponseDto getById(Long id);

    CategoryResponseDto save(CreateCategoryRequestDto
                                     createCategoryRequestDto);

    CategoryResponseDto update(Long id,
                               CreateCategoryRequestDto createCategoryRequestDto);

    void deleteById(Long id);

    List<BookDtoWithoutCategoryIds> getBooksByCategoriesId(Long id);
}
