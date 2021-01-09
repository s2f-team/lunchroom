package team.s2f.lunchroom.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import team.s2f.lunchroom.AuthorizedUser;
import team.s2f.lunchroom.dto.UserTo;
import team.s2f.lunchroom.model.User;
import team.s2f.lunchroom.repository.UserRepository;

import java.util.List;

@Service("userService")
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

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

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.getByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User with email " + email + " is not found.");
        }
        return new AuthorizedUser(user);
    }
}
