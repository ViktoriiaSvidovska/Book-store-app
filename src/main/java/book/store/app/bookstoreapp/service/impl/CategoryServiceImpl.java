package book.store.app.bookstoreapp.service.impl;

import book.store.app.bookstoreapp.dto.book.BookDtoNoCategory;
import book.store.app.bookstoreapp.dto.category.CategoryResponseDto;
import book.store.app.bookstoreapp.dto.category.CreateCategoryRequestDto;
import book.store.app.bookstoreapp.exception.EntityNotFoundException;
import book.store.app.bookstoreapp.mapper.BookMapper;
import book.store.app.bookstoreapp.mapper.CategoryMapper;
import book.store.app.bookstoreapp.model.Category;
import book.store.app.bookstoreapp.repository.category.CategoryRepository;
import book.store.app.bookstoreapp.service.CategoryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final BookMapper bookMapper;

    @Override
    public List<CategoryResponseDto> findAll(Pageable pageable) {
        return categoryRepository.findAll(pageable).stream()
                .map(categoryMapper::toDto).toList();
    }

    @Override
    public CategoryResponseDto getById(Long id) {
        return categoryMapper.toDto(categoryRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Can't get category by id: " + id)));
    }

    @Override
    public CategoryResponseDto save(CreateCategoryRequestDto categoryDto) {
        return categoryMapper.toDto(categoryRepository.save(categoryMapper
                .toModel(categoryDto)));
    }

    @Override
    public CategoryResponseDto update(Long id, CreateCategoryRequestDto categoryDto) {
        if (!categoryRepository.existsById(id)) {
            throw new EntityNotFoundException("Can't update category by id: " + id);
        }
        Category category = categoryMapper.toModel(categoryDto);
        category.setId(id);
        return categoryMapper.toDto(categoryRepository.save(category));
    }

    @Override
    public void deleteById(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new EntityNotFoundException("Can't delete book by id: " + id);
        }
        categoryRepository.deleteById(id);
    }

    @Override
    public List<BookDtoNoCategory> getBooksByCategoriesId(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new EntityNotFoundException("Category not found: " + id);
        }
        return categoryRepository.getBooksByCategoriesId(id).stream()
                .map(bookMapper::toDtoWithoutCategories)
                .toList();
    }
}
