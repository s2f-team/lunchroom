package team.s2f.lunchroom.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import team.s2f.lunchroom.UserTestData;
import team.s2f.lunchroom.dto.UserTo;
import team.s2f.lunchroom.model.Role;
import team.s2f.lunchroom.model.User;
import team.s2f.lunchroom.util.exception.NotFoundException;

import javax.validation.ConstraintViolationException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public class UserServiceTest extends AbstractServiceTest {

    @Autowired
    protected UserService userService;

    @Test
    void create() {
        User created = userService.create(UserTestData.getNew());
        int createdId = created.id();
        User newUser = UserTestData.getNew();
        newUser.setId(createdId);
        UserTestData.USER_MATCHER.assertMatch(created, newUser);
        UserTestData.USER_MATCHER.assertMatch(userService.get(createdId), newUser);
    }

    @Test
    void createWithException() {
        validateRootCause(() -> userService.create(null), IllegalArgumentException.class);
        validateRootCause(() -> userService.create(new User(null, "  ", "new@gmail.com", "newPass", Role.USER)), ConstraintViolationException.class);
        validateRootCause(() -> userService.create(new User(null, "A", "new@gmail.com", "newPass", Role.USER)), ConstraintViolationException.class);
        validateRootCause(() -> userService.create(new User(null, "New", "  ", "newPass", Role.USER)), ConstraintViolationException.class);
        validateRootCause(() -> userService.create(new User(null, "New", "not a email", "newPass", Role.USER)), ConstraintViolationException.class);
        validateRootCause(() -> userService.create(new User(null, "New", "new@gmail.com", "  ", Role.USER)), ConstraintViolationException.class);
    }

    @Test
    void duplicateMailCreate() {
        Assertions.assertThrows(DataAccessException.class, () ->
                userService.create(new User(null, "Duplicate", "1@mail.ru", "newPass", LocalDate.now(), true, Collections.singleton(Role.USER))));
    }

    @Test
    void update() {
        User updated = UserTestData.getUpdated();
        userService.update(updated);
        UserTestData.USER_MATCHER.assertMatch(userService.get(UserTestData.USER_ID1), updated);
    }

    @Test
    void updateByUserTo() {
        UserTo updated = UserTestData.getUpdatedTo();
        userService.update(updated);
        UserTestData.USER_MATCHER.assertMatch(userService.get(UserTestData.USER_ID1), UserTestData.user1UpdatedFromTo);
    }

    @Test
    void delete() {
        userService.delete(UserTestData.USER_ID1);
        Assertions.assertThrows(NotFoundException.class, () -> userService.get(UserTestData.USER_ID1));
    }

    @Test
    void deleteNotFound() {
        Assertions.assertThrows(NotFoundException.class, () -> userService.delete(UserTestData.NOT_FOUND));
    }

    @Test
    void get() {
        User user = userService.get(UserTestData.ADMIN_ID);
        UserTestData.USER_MATCHER.assertMatch(user, UserTestData.admin);
    }

    @Test
    void getNotFound() {
        Assertions.assertThrows(NotFoundException.class, () -> userService.get(UserTestData.NOT_FOUND));
    }

    @Test
    void getAll() {
        List<User> users = userService.getAll();
        UserTestData.USER_MATCHER.assertMatch(users, UserTestData.getAll);
    }

    @Test
    void enable() {
        userService.enable(UserTestData.USER_ID1, false);
        Assertions.assertFalse(userService.get(UserTestData.USER_ID1).getEnabled());
        userService.enable(UserTestData.USER_ID1, true);
        Assertions.assertTrue(userService.get(UserTestData.USER_ID1).getEnabled());
    }
}