package book.store.app.bookstoreapp.service.impl;

import book.store.app.bookstoreapp.dto.user.UserResponseDto;
import book.store.app.bookstoreapp.dto.user.UserRegistrationRequestDto;
import book.store.app.bookstoreapp.exception.RegistrationException;
import book.store.app.bookstoreapp.mapper.UserMapper;
import book.store.app.bookstoreapp.model.User;
import book.store.app.bookstoreapp.repository.UserRepository;
import book.store.app.bookstoreapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponseDto register(UserRegistrationRequestDto requestDto) {
        if (userRepository.findByEmail(requestDto.getEmail()).isPresent()) {
            throw new RegistrationException("This email is already used");
        }
        User user = userMapper.toModel(requestDto);
        user.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        return userMapper.toDto(userRepository.save(user));
    }
}
