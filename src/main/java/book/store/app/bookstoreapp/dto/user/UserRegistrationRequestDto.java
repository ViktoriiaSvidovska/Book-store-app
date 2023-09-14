package book.store.app.bookstoreapp.dto.user;

import book.store.app.bookstoreapp.lib.FieldMatch;
import book.store.app.bookstoreapp.lib.Password;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@FieldMatch(
        field = "password",
        fieldMatch = "repeatPassword",
        message = "Passwords do not match!"
)
public class UserRegistrationRequestDto {
    @Email(message = "Please provide a valid email address")
    @Size(min = 8, max = 25, message = "Email length must be between 8 and 50 characters")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 8, max = 25, message = "Password length must be between 8 and 20 characters")
    @Password(message = "Password must meet specific criteria")
    private String password;

    @NotBlank(message = "Password confirmation is required")
    private String repeatPassword;

    @NotBlank(message = "First name is required")
    @Size(min = 2, max = 35, message = "First name length must be between 2 and 35 characters")
    private String firstName;

    @Size(min = 2, max = 35, message = "Last name length must be between 2 and 35 characters")
    private String lastName;

    @NotBlank(message = "Shipping address is required")
    @Size(min = 5, max = 100,
            message = "Shipping address length must be between 5 and 90 characters")
    private String shippingAddress;
}
