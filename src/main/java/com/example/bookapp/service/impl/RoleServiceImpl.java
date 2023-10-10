package com.example.bookapp.service.impl;

import com.example.bookapp.model.Role;
import com.example.bookapp.model.RoleName;
import com.example.bookapp.repository.user.RoleRepository;
import com.example.bookapp.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public Role getRoleByRoleName(RoleName roleName) {
        return roleRepository.findRoleByRoleName(roleName).orElseThrow(() ->
                new RuntimeException("can't find role by roleName: " + roleName));
    }
}
