package book.store.app.bookstoreapp.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private RoleName roleName;
}
enum RoleName {
    ROLE_USER, ROLE_ADMIN
}
