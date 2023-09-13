package book.store.app.bookstoreapp.service.impl;

import book.store.app.bookstoreapp.model.Role;
import book.store.app.bookstoreapp.model.RoleName;
import book.store.app.bookstoreapp.repository.role.RoleRepository;
import book.store.app.bookstoreapp.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public Role getRoleByRoleName(RoleName roleName) {
        return roleRepository.findRoleByRoleName(roleName).orElseThrow(() ->
                new RuntimeException("Can't find role by name: " + roleName));
    }
}
