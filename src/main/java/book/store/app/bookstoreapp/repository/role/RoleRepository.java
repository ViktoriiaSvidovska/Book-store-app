package book.store.app.bookstoreapp.repository.role;

import book.store.app.bookstoreapp.model.Role;
import book.store.app.bookstoreapp.model.RoleName;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findRoleByRoleName(RoleName roleName);
}
