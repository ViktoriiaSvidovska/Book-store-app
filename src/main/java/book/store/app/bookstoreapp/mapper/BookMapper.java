package book.store.app.bookstoreapp.mapper;

import book.store.app.bookstoreapp.config.MapperConfig;
import book.store.app.bookstoreapp.dto.book.BookDtoWithoutCategoryIds;
import book.store.app.bookstoreapp.dto.book.BookResponseDto;
import book.store.app.bookstoreapp.dto.book.CreateBookRequestDto;
import book.store.app.bookstoreapp.model.Book;
import book.store.app.bookstoreapp.model.Category;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class)
public interface BookMapper {
    @Mapping(target = "id", ignore = true)
    Book toModel(CreateBookRequestDto createBookRequestDto);

    BookResponseDto toDto(Book book);

    BookDtoWithoutCategoryIds toDtoWithoutCategories(Book book);

    @AfterMapping
    default void setCategoryIds(@MappingTarget BookResponseDto bookResponseDto, Book book) {
        bookResponseDto.setCategoryIds(book.getCategories()
                .stream()
                .map(Category::getId)
                .toList());
    }
}
