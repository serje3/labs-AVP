package entity;

import base.abstracts.Entity;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.FieldNameConstants;

@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = {"username"}, callSuper = false)
@FieldNameConstants
public class User extends Entity {
    private String username;

    private String password;

    public boolean isSuperUser() {
        return false;
    }
}
