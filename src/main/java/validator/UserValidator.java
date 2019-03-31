package validator;

import entity.User;
import exceptions.UserShortLengthLoginException;
import exceptions.UserShortLengthPasswordException;

public class UserValidator {
    private static final int MIN_LENGTH_PASSWORD = 1;
    private static final int MIN_LENGTH_LOGIN = 1;

    private static UserValidator instance = null;

    private UserValidator() {
    }

    public static UserValidator getInstance() {
        if (instance == null) {
            instance = new UserValidator();
        }
        return instance;
    }

    public boolean isValidate(User user) throws UserShortLengthLoginException, UserShortLengthPasswordException {
        if (isPasswordLengthEnough(user.getPassword())) {
            if (isNameLengthEnough(user.getLogin())) {
                return true;
            }
            throw new UserShortLengthLoginException("Login is too short");
        } else {
            throw new UserShortLengthPasswordException("Password is too short");
        }
    }

    private boolean isPasswordLengthEnough(String password) {
        return password.length() >= MIN_LENGTH_PASSWORD;
    }

    private boolean isNameLengthEnough(String login) {
        return login.length() >= MIN_LENGTH_LOGIN;
    }

}