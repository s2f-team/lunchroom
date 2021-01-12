package team.s2f.lunchroom.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "users")
public class User extends AbstractBaseEntity {

    @Column(name = "name", nullable = false)
    @NotBlank
    @Size(min = 2, max = 25)
    String name;

    @Column(name = "email", nullable = false, unique = true)
    @NotBlank
    @Email
    @Size(max = 100)
    String email;

    @Column(name = "password", nullable = false)
    @NotBlank
    @Size(min = 5, max = 100)
    String password;

    @Column(name = "date", nullable = false)
    @NotNull
    LocalDate registration;

    @Column(name = "enabled", nullable = false, columnDefinition = "bool default true")
    Boolean enabled;

    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "role"}, name = "user_roles_unique_idx")})
    @Column(name = "role")
    @ElementCollection(fetch = FetchType.EAGER)
    Set<Role> roles;

    public User(Integer id, String name, String email, String password, LocalDate registration, Boolean enabled, Set<Role> roles) {
        super(id);
        this.name = name;
        this.email = email;
        this.password = password;
        this.registration = registration;
        this.enabled = enabled;
        this.roles = roles;
    }
}
