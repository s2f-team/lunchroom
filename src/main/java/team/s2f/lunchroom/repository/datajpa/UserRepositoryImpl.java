package team.s2f.lunchroom.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import team.s2f.lunchroom.model.User;
import team.s2f.lunchroom.repository.UserRepository;
import team.s2f.lunchroom.repository.datajpa.UserCrud;

import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private final UserCrud userCrud;

    @Autowired
    public UserRepositoryImpl(UserCrud userCrud) {
        this.userCrud = userCrud;
    }

    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public User getById(int id) {
        return null;
    }

    @Override
    public User getByEmail(String email) {
        return userCrud.getByEmail(email);
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public List<User> getAllWithVotes() {
        return null;
    }
}
