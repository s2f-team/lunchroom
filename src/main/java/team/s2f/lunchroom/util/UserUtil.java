package team.s2f.lunchroom.util;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import team.s2f.lunchroom.dto.UserTo;
import team.s2f.lunchroom.model.Role;
import team.s2f.lunchroom.model.User;

import java.time.LocalDate;
import java.util.Set;

public class UserUtil {
    public static User createNewFromTo(UserTo userTo) {
        Set<Role> roleSet = Set.of(Role.USER);
        return new User(null, userTo.getName(), userTo.getEmail().toLowerCase(), userTo.getPassword(), LocalDate.now(), true, roleSet);
    }

    public static UserTo asTo(User user) {
        return new UserTo(user.getId(), user.getName(), user.getEmail(), user.getPassword());
    }

    public static User updateFromTo(User user, UserTo userTo) {
        user.setName(userTo.getName());
        user.setEmail(userTo.getEmail().toLowerCase());
        user.setPassword(userTo.getPassword());
        return user;
    }

    public static User prepareToSave(User user, PasswordEncoder passwordEncoder) {
        String password = user.getPassword();
        user.setPassword(StringUtils.hasText(password) ? passwordEncoder.encode(password) : password);
        user.setEmail(user.getEmail().toLowerCase());
        return user;
    }
}