package com.epam.gameshop.util.validator;


import com.epam.gameshop.entity.User;

public class UserValidator implements Validator<User> {
    public static final String LOGIN_REGEX = "^[a-zA-Z][a-zA-Z0-9-]{1,20}$";
    public static final String NAME_REGEX = "^[а-яА-ЯёЁa-zA-Z '-]+$";
    public static final String PASSWORD_REGEX = "^(?=.*\\d)(?=.*[a-z])(?!.*\\s).*$";
    public static final String EMAIL_REGEX = "([.[^@\\s]]+)@([.[^@\\s]]+)\\.([a-z]+)";

    public boolean isValid(User user) {
        boolean answer = false;
        if (user != null && validateString(user.getLogin(), LOGIN_REGEX) && validateString(user.getPassword(), PASSWORD_REGEX) &&
                validateString(user.getName(), NAME_REGEX) && validateString(user.getsName(), NAME_REGEX) &&
                validateString(user.geteMail(), EMAIL_REGEX)) {
            answer = true;
        }
        return answer;
    }

}
