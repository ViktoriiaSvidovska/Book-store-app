package book.store.app.bookstoreapp.service;

import book.store.app.bookstoreapp.dto.user.UserRegistrationRequestDto;
import book.store.app.bookstoreapp.dto.user.UserResponseDto;

public interface UserService {
    UserResponseDto register(UserRegistrationRequestDto requestDto);
}
