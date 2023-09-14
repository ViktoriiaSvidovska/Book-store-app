package book.store.app.bookstoreapp.service.impl;

import book.store.app.bookstoreapp.dto.user.UserRegistrationRequestDto;
import book.store.app.bookstoreapp.dto.user.UserResponseDto;
import book.store.app.bookstoreapp.exception.RegistrationException;
import book.store.app.bookstoreapp.mapper.UserMapper;
import book.store.app.bookstoreapp.model.Role;
import book.store.app.bookstoreapp.model.RoleName;
import book.store.app.bookstoreapp.model.User;
import book.store.app.bookstoreapp.repository.user.UserRepository;
import book.store.app.bookstoreapp.service.RoleService;
import book.store.app.bookstoreapp.service.UserService;
import java.util.HashSet;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;

    @Override
    public UserResponseDto register(UserRegistrationRequestDto userRegistrationRequestDto) {
        if (userRepository.findByEmail(userRegistrationRequestDto.getEmail()).isPresent()) {
            throw new RegistrationException("Email is already used");
        }
        User user = userMapper.toModel(userRegistrationRequestDto);
        user.setPassword(passwordEncoder.encode(userRegistrationRequestDto.getPassword()));
        Role userRole = roleService.getRoleByRoleName(RoleName.ROLE_USER);
        user.setRoles(new HashSet<>(Set.of(userRole)));
        return userMapper.toDto(userRepository.save(user));
    }
}
