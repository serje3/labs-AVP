package database;

import base.abstracts.Model;
import entity.SuperUser;
import entity.User;
import exceptions.PasswordsNotMatchException;
import exceptions.UserAlreadyExistException;
import base.interfaces.IModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import utils.SecurityUtility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

@Getter
@NoArgsConstructor
public class UserModel extends Model<User, String> {
    private static final HashMap<String, User> users = new HashMap<>() {{
        put("admin", new SuperUser("admin", SecurityUtility.encode("foobarbaz")));
    }};


    public boolean authenticate(String username, String password) {
        try {
            User user = this.get(username);
            return user.getPassword().equals(SecurityUtility.encode(password));
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void create(String username, String password1, String password2) throws UserAlreadyExistException, PasswordsNotMatchException {
        if (users.containsKey(username)) {
            throw new UserAlreadyExistException();
        } else if (!password1.equals(password2)){
            throw new PasswordsNotMatchException();
        }
        String encryptedPassword = SecurityUtility.encode(password1);
        this.create(new User(username, encryptedPassword));
    }

    @Override
    protected void create(User user) {
        users.put(user.getUsername(), user);
    }

    @Override
    public User get(String username) throws NoSuchElementException {
        if (users.containsKey(username)) {
            return users.get(username);
        } else {
            throw new NoSuchElementException("User not exist");
        }
    }

    @Override
    public void delete(String key) {
        users.remove(key);
    }

    @Override
    public List<User> list() {
        return new ArrayList<>(users.values());
    }
}
