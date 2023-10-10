package com.example.bookapp.service;

import com.example.bookapp.dto.book.BookDtoWithoutCategoryIds;
import com.example.bookapp.dto.category.CategoryResponseDto;
import com.example.bookapp.dto.category.CreateCategoryDto;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface CategoryService {
    List<CategoryResponseDto> findAll(Pageable pageable);

    CategoryResponseDto getById(Long id);

    CategoryResponseDto save(CreateCategoryDto categoryDto);

    CategoryResponseDto update(Long id, CreateCategoryDto categoryDto);

    void deleteById(Long id);

    List<BookDtoWithoutCategoryIds> getBooksByCategoriesId(Long id);
}
