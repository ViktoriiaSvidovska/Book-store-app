package book.store.app.bookstoreapp.service;

import book.store.app.bookstoreapp.model.Role;
import book.store.app.bookstoreapp.model.RoleName;

public interface RoleService {
    Role getRoleByRoleName(RoleName roleName);
}
