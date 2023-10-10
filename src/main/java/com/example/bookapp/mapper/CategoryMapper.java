package com.example.bookapp.mapper;

import com.example.bookapp.config.MapperConfig;
import com.example.bookapp.dto.category.CategoryResponseDto;
import com.example.bookapp.dto.category.CreateCategoryDto;
import com.example.bookapp.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface CategoryMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "books", ignore = true)
    Category toModel(CreateCategoryDto requestDto);

    CategoryResponseDto toDto(Category category);
}
