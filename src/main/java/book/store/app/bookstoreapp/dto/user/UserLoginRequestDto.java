package book.store.app.bookstoreapp.dto.user;

import book.store.app.bookstoreapp.lib.PasswordValidator;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record UserLoginRequestDto(
        @Size(min = 8, max = 25, message = "Email length must be between 8 and 25 characters")
        @Email(message = "Please enter a valid email address")
        String email,

        @PasswordValidator (message = "Password must meet specific criteria")
        String password
) {
}
