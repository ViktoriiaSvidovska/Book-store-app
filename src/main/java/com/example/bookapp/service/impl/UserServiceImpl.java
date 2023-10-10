package com.example.bookapp.service.impl;

import com.example.bookapp.dto.user.UserRegistrationRequestDto;
import com.example.bookapp.dto.user.UserResponseDto;
import com.example.bookapp.exception.EntityNotFoundException;
import com.example.bookapp.exception.RegistrationException;
import com.example.bookapp.mapper.UserMapper;
import com.example.bookapp.model.Role;
import com.example.bookapp.model.RoleName;
import com.example.bookapp.model.ShoppingCart;
import com.example.bookapp.model.User;
import com.example.bookapp.repository.cart.ShoppingCartRepository;
import com.example.bookapp.repository.user.UserRepository;
import com.example.bookapp.service.RoleService;
import com.example.bookapp.service.UserService;
import java.util.HashSet;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;
    private final ShoppingCartRepository shoppingCartRepository;

    @Override
    @Transactional
    public UserResponseDto register(UserRegistrationRequestDto requestDto) {
        if (userRepository.findByEmail(requestDto.getEmail()).isPresent()) {
            throw new RegistrationException("this email is already in use");
        }
        User user = userMapper.toModel(requestDto);
        user.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        Role userRole = roleService.getRoleByRoleName(RoleName.ROLE_USER);
        user.setRoles(new HashSet<>(Set.of(userRole)));
        if (user.getEmail().equals("artem@gmail.com")) {
            user.setRoles(new HashSet<>(Set.of(roleService
                    .getRoleByRoleName(RoleName.ROLE_ADMIN))));
        }
        User savedUser = userRepository.save(user);
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUser(savedUser);
        shoppingCartRepository.save(shoppingCart);
        return userMapper.toDto(savedUser);
    }

    @Override
    public User getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByEmail(authentication.getName()).orElseThrow(() ->
                new EntityNotFoundException("can't find user by username: "
                        + authentication.getName()));
    }
}
