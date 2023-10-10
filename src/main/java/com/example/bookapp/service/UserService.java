package com.example.bookapp.service;

import com.example.bookapp.dto.user.UserRegistrationRequestDto;
import com.example.bookapp.dto.user.UserResponseDto;
import com.example.bookapp.model.User;

public interface UserService {
    UserResponseDto register(UserRegistrationRequestDto requestDto);

    User getUser();
}
