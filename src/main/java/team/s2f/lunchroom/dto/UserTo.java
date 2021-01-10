package team.s2f.lunchroom.dto;

import lombok.*;
import team.s2f.lunchroom.HasIdAndEmail;

import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserTo extends BaseTo implements HasIdAndEmail, Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String email;
    private String password;

    public UserTo(Integer id, String name, String email, String password) {
        super(id);
        this.name = name;
        this.email = email;
        this.password = password;
    }

    @Override
    public boolean isNew() {
        return false;
    }

    @Override
    public int id() {
        return 0;
    }

    @Override
    public String getEmail() {
        return null;
    }
}
