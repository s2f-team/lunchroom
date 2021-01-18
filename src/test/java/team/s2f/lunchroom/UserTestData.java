package team.s2f.lunchroom;

import team.s2f.lunchroom.dto.UserTo;
import team.s2f.lunchroom.model.Role;
import team.s2f.lunchroom.model.User;
import team.s2f.lunchroom.web.json.JsonUtil;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class UserTestData {

    public static final TestMatcher<User> USER_MATCHER = TestMatcher.usingIgnoringFieldsComparator(User.class, "password", "registration");

    public static final int USER_ID1 = 100000;
    public static final int USER_ID2 = 100001;
    public static final int ADMIN_ID = 100002;
    public static final int NOT_FOUND = 10;

    public static final User user1 = new User(USER_ID1, "User1", "1@mail.ru", "password", LocalDate.now(), true, Set.of(Role.USER));
    public static final User user2 = new User(USER_ID2, "User2", "2@mail.ru", "password", LocalDate.now(), true, Set.of(Role.USER));
    public static final User admin = new User(ADMIN_ID, "Admin", "3@mail.ru", "password", LocalDate.now(), true, Set.of(Role.USER, Role.ADMIN));

    public static final User user1UpdatedFromTo = new User(USER_ID1, "User1_upd", "11@mail.ru", "password", LocalDate.now(), true, Set.of(Role.USER));

    public static User getNew() {
        return new User(null, "New", "new@gmail.com", "newPass", LocalDate.now(), true, Collections.singleton(Role.USER));
    }

    public static User getUpdated() {
        User updated = new User(user1);
        updated.setName("UpdatedName");
        updated.setPassword("newPass");
        updated.setEnabled(false);
        updated.setRoles(Set.of(Role.ADMIN));
        return updated;
    }

    public static UserTo getUpdatedTo() {
        return new UserTo(USER_ID1, "User1_upd", "11@mail.ru", "password");
    }

    public static List<User> getAll = List.of(user1, user2, admin);

    public static String jsonWithPassword(User user, String passw) {
        return JsonUtil.writeAdditionProps(user, "password", passw);
    }
}
