package team.s2f.lunchroom.service;

import org.springframework.stereotype.Service;
import team.s2f.lunchroom.dto.UserTo;
import team.s2f.lunchroom.model.User;

import java.util.List;

@Service
public class UserService {

    public User create(UserTo userTo) {
        return null;
    }

    public void update(UserTo userTo) {
    }

    public UserTo getById(int id) {
        return null;
    }

    public User getByEmail(String email) {
        return null;
    }

    public List<User> getAll() {
        return null;
    }

    public void enable(int id, boolean enabled) {
    }

    public void delete(int id) {
    }
}
