package entity;

import lombok.Getter;


@Getter
public class SuperUser extends User {

    public SuperUser(String username, String password) {
        super(username, password);
    }

    @Override
    public boolean isSuperUser() {
        return true;
    }
}
