package book.store.app.bookstoreapp.service.impl;

import book.store.app.bookstoreapp.dto.user.UserRegistrationRequestDto;
import book.store.app.bookstoreapp.dto.user.UserResponseDto;
import book.store.app.bookstoreapp.exception.EntityNotFoundException;
import book.store.app.bookstoreapp.exception.RegistrationException;
import book.store.app.bookstoreapp.mapper.UserMapper;
import book.store.app.bookstoreapp.model.Role;
import book.store.app.bookstoreapp.model.RoleName;
import book.store.app.bookstoreapp.model.ShoppingCart;
import book.store.app.bookstoreapp.model.User;
import book.store.app.bookstoreapp.repository.shoppingcart.ShoppingCartRepository;
import book.store.app.bookstoreapp.repository.user.UserRepository;
import book.store.app.bookstoreapp.service.RoleService;
import book.store.app.bookstoreapp.service.UserService;
import java.util.HashSet;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;
    private final ShoppingCartRepository shoppingCartRepository;

    @Override
    public UserResponseDto register(UserRegistrationRequestDto requestDto) {
        if (userRepository.findByEmail(requestDto.getEmail()).isPresent()) {
            throw new RegistrationException("This email is already used");
        }
        User user = userMapper.toModel(requestDto);
        user.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        Role userRole = roleService.getRoleByRoleName(RoleName.ROLE_USER);
        user.setRoles(new HashSet<>(Set.of(userRole)));
        if (user.getEmail().equals("g@g.com")) {
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
                new EntityNotFoundException("Can't find user: "
                        + authentication.getName()));
    }
}
