package book.store.app.bookstoreapp.mapper;

import book.store.app.bookstoreapp.config.MapperConfig;
import book.store.app.bookstoreapp.dto.book.BookDtoWithoutCategoryIds;
import book.store.app.bookstoreapp.dto.book.BookResponseDto;
import book.store.app.bookstoreapp.dto.book.CreateBookRequestDto;
import book.store.app.bookstoreapp.model.Book;
import book.store.app.bookstoreapp.model.Category;
import org.mapstruct.*;

@Mapper(config = MapperConfig.class)
public interface BookMapper {
    Book toModel(CreateBookRequestDto createBookRequestDto);

    BookResponseDto toDto(Book book);

    BookDtoWithoutCategoryIds toDtoWithoutCategories(Book book);

    @BeforeMapping
    default void mapCategoriesToCategoryIds(Book book, @MappingTarget BookResponseDto bookResponseDto) {
        bookResponseDto.setCategoryIds(
                book.getCategories()
                        .stream()
                        .map(Category::getId)
                        .toList()
        );
    }

    @AfterMapping
    default void setCategoryIds(@MappingTarget BookResponseDto bookResponseDto, Book book) {
        bookResponseDto.setCategoryIds(book.getCategories()
                .stream()
                .map(Category::getId)
                .toList());
    }
}
