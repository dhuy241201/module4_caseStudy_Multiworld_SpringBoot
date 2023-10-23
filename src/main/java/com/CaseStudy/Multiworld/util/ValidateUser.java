package com.CaseStudy.Multiworld.util;

import com.CaseStudy.Multiworld.entity.User.User;
import org.springframework.boot.autoconfigure.ldap.embedded.EmbeddedLdapProperties;

public class ValidateUser {
    private static final String USER_REGEX = "^[a-z0-9._]{3,15}$";
    private static final String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
    private static final String NAME_REGEX = "^[A-Za-z0-9_.]{3,15}$";
    private static final String EMAIL_REGEX = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
    public static boolean validate(User user){
        String username = user.getUsername();
        String password = user.getPassword();
        String name = user.getName();
        String email = user.getEmail();

        boolean isUsernameValid = username.matches(USER_REGEX);
        boolean isPasswordValid = password.matches(PASSWORD_REGEX);
        boolean isNameValid = name.matches(NAME_REGEX);
        boolean isEmailValid = email.matches(EMAIL_REGEX);

        return isUsernameValid && isPasswordValid && isNameValid && isEmailValid;
    }
}
