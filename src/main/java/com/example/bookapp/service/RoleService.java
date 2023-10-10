package com.example.bookapp.service;

import com.example.bookapp.model.Role;
import com.example.bookapp.model.RoleName;

public interface RoleService {
    Role getRoleByRoleName(RoleName roleName);
}
