package team.s2f.lunchroom.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
public class User extends AbstractBaseEntity {
    String name;
    String email;
    String password;
    LocalDate registration;
    Boolean enabled;
    List<Role> roles;
}
