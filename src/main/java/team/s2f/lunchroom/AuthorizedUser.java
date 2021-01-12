package team.s2f.lunchroom;


import team.s2f.lunchroom.model.User;

public class AuthorizedUser extends org.springframework.security.core.userdetails.User {
    private static final long serialVersionUID = 1L;
    protected User user;

   /* private UserTo userTo;*/

    public AuthorizedUser(User user) {
        super(user.getEmail(), user.getPassword(), user.getEnabled(), true, true, true, user.getRoles());
        /*this.userTo = UserUtil.asTo(user);*/
        this.user = user;
    }

    public int getId() {
        return user.getId();
    }

    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        return user.toString();
    }

   /* public int getId() {
        return userTo.id();
    }

    public UserTo getUserTo() {
        return userTo;
    }

    @Override
    public String toString() {
        return userTo.toString();
    }*/
}
