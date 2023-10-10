package com.example.bookapp.mapper;

import com.example.bookapp.config.MapperConfig;
import com.example.bookapp.dto.user.UserRegistrationRequestDto;
import com.example.bookapp.dto.user.UserResponseDto;
import com.example.bookapp.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface UserMapper {
    UserResponseDto toDto(User user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "authorities", ignore = true)
    User toModel(UserRegistrationRequestDto requestDto);
}
