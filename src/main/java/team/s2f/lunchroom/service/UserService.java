package team.s2f.lunchroom.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import team.s2f.lunchroom.AuthorizedUser;
import team.s2f.lunchroom.dto.UserTo;
import team.s2f.lunchroom.model.User;
import team.s2f.lunchroom.repository.UserRepository;
import team.s2f.lunchroom.util.UserUtil;
import team.s2f.lunchroom.util.ValidationUtil;

import java.util.List;

@Service("userService")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User create(User user) {
        Assert.notNull(user, "user must not be null");
        return prepareAndSave(user);
    }

    public void update(User user) {
        Assert.notNull(user, "user must not be null");
        prepareAndSave(user);
    }

    @Transactional
    public void update(UserTo userTo) {
        User user = get(userTo.getId());
        prepareAndSave(UserUtil.updateFromTo(user, userTo));
    }

    public void delete(int id) {
        ValidationUtil.checkNotFoundWithId(userRepository.delete(id), id);
    }

    public User get(int id) {
        return ValidationUtil.checkNotFoundWithId(userRepository.getById(id), id);
    }

    public User getByEmail(String email) {
        Assert.notNull(email, "email must not be null");
        return ValidationUtil.checkNotFound(userRepository.getByEmail(email), "email=" + email);
    }

    public List<User> getAll() {
        return userRepository.getAll();
    }

    @Transactional
    public void enable(int id, boolean enabled) {
        User user = userRepository.getById(id);
        user.setEnabled(enabled);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.getByEmail(email.toLowerCase());
        System.out.println(user);
        if (user == null) {
            throw new UsernameNotFoundException("User with email " + email + " is not found.");
        }
        return new AuthorizedUser(user);
    }

    private User prepareAndSave(User user) {
        return userRepository.save(UserUtil.prepareToSave(user, passwordEncoder));
    }
}
