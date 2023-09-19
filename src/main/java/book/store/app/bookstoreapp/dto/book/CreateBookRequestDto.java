package book.store.app.bookstoreapp.dto.book;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.List;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CreateBookRequestDto {
    @NotBlank(message = "Title cannot be empty")
    private String title;
    @NotBlank(message = "Author cannot be empty")
    private String author;
    @NotBlank(message = "ISBN cannot be empty")
    private String isbn;
    @NotBlank(message = "Price cannot be empty")
    @Positive
    private BigDecimal price;
    private List<Long> categoryIds;
    private String description;
    private String coverImage;
}
