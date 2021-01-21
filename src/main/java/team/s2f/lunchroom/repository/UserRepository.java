package team.s2f.lunchroom.repository;

import team.s2f.lunchroom.model.User;

import java.util.List;

public interface UserRepository {
    User save(User user);

    boolean delete(int id);

    User getById(int id);

    User getByEmail(String email);

    List<User> getAll();
}