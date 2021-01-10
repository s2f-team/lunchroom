package team.s2f.lunchroom.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import team.s2f.lunchroom.model.User;
import team.s2f.lunchroom.repository.UserRepository;

import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private final UserCrud userCrud;

    @Autowired
    public UserRepositoryImpl(UserCrud userCrud) {
        this.userCrud = userCrud;
    }

    @Override
    @Transactional
    public User save(User user) {
        return userCrud.save(user);
    }

    @Override
    public boolean delete(int id) {
        return userCrud.delete(id) != 0;
    }

    @Override
    public User getById(int id) {
        return userCrud.findById(id).orElse(null);
    }

    @Override
    public User getByEmail(String email) {
        return userCrud.getByEmail(email);
    }

    @Override
    public List<User> getAll() {
        return userCrud.findAll();
    }
}