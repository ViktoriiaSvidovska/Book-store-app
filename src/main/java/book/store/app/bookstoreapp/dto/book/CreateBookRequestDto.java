package book.store.app.bookstoreapp.dto.book;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class CreateBookRequestDto {
    @NotBlank(message = "Title cannot be empty")
    private String title;
    @NotBlank(message = "Author cannot be empty")
    private String author;
    @NotBlank(message = "ISBN cannot be empty")
    private String isbn;
    @NotBlank(message = "Price cannot be empty")
    @Min(0)
    private BigDecimal price;
    private String description;
    private String coverImage;
}
