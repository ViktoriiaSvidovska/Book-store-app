package book.store.app.bookstoreapp.mapper;

import book.store.app.bookstoreapp.config.MapperConfig;
import book.store.app.bookstoreapp.dto.category.CategoryResponseDto;
import book.store.app.bookstoreapp.dto.category.CreateCategoryRequestDto;
import book.store.app.bookstoreapp.model.Book;
import book.store.app.bookstoreapp.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface CategoryMapper {
    Category toModel(CreateCategoryRequestDto createCategoryRequestDto);

    CategoryResponseDto toDto(Category category);

}
