package book.store.app.bookstoreapp.dto.category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateCategoryRequestDto {
    @NotBlank(message = "Title cannot be empty")
    @Size(min = 1, max = 35)
    private String name;
    @NotBlank(message = "Description cannot be empty")
    private String description;
}
