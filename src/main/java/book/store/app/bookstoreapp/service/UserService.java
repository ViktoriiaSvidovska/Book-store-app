package book.store.app.bookstoreapp.service;

import book.store.app.bookstoreapp.dto.user.UserRegistrationRequestDto;
import book.store.app.bookstoreapp.dto.user.UserResponseDto;
import book.store.app.bookstoreapp.model.User;

public interface UserService {
    UserResponseDto register(UserRegistrationRequestDto requestDto);

    User getUser();
}
