package io.wangxiao.auth.domain.dto;


import io.wangxiao.auth.domain.user.Privilege;
import io.wangxiao.auth.domain.user.User;
import io.wangxiao.auth.infrastructure.PasswordHandler;

/**
 * 2016/3/25
 *
 * @author Shengzhao Li
 */
public class UserFormDto extends UserDto {
    private static final long serialVersionUID = 7959857016962260738L;


    private String password;

    public UserFormDto() {
    }


    public Privilege[] getAllPrivileges() {
        return new Privilege[]{Privilege.MOBILE, Privilege.UNITY};
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User newUser() {
        final User user = new User()
                .username(getUsername())
                .phone(getPhone())
                .email(getEmail())
                .password(PasswordHandler.md5(getPassword()));
        user.privileges().addAll(getPrivileges());
        return user;
    }
}
