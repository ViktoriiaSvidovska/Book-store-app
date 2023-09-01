package book.store.app.bookstoreapp.mapper;

import book.store.app.bookstoreapp.config.MapperConfig;
import book.store.app.bookstoreapp.dto.book.BookResponseDto;
import book.store.app.bookstoreapp.dto.book.CreateBookRequestDto;
import book.store.app.bookstoreapp.model.Book;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface BookMapper {
    Book toModel(CreateBookRequestDto requestDto);

    BookResponseDto toDto(Book book);
}
