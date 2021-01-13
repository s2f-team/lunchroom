package team.s2f.lunchroom.dto;

import lombok.*;
import team.s2f.lunchroom.HasIdAndEmail;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserTo extends BaseTo implements HasIdAndEmail, Serializable {
    private static final long serialVersionUID = 1L;

    @NotEmpty
    private String name;

    @NotEmpty
    @Email
    private String email;

    @NotNull
    @Size(min = 5, max = 100)
    private String password;

    public UserTo(Integer id, String name, String email, String password) {
        super(id);
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
