package validator;

import api.UserDao;
import dao.UserDaoImpl;
import entity.User;
import exceptions.UserLoginAlreadyExistException;
import exceptions.UserShortLengthLoginException;
import exceptions.UserShortLengthPasswordException;

import java.io.IOException;

public class UserValidator {
    private static final int MIN_LENGTH_PASSWORD = 6;
    private static final int MIN_LENGTH_LOGIN = 4;
    private static UserValidator instance = new UserValidator();
    private static UserDao userDao = UserDaoImpl.getInstance();

    private UserValidator() {
    }

    public static UserValidator getInstance() {
        if (instance == null) {
            instance = new UserValidator();
        }
        return instance;
    }

    public static boolean isValidate(User user) throws UserLoginAlreadyExistException, UserShortLengthLoginException, UserShortLengthPasswordException {
        if (isPasswordLongEnough(user.getPassword()))
            throw new UserShortLengthPasswordException("Password is too short");
        if (isNameLongEnough(user.getLogin()))
            throw new UserShortLengthLoginException("Login is too short");
        if (isLoginAlreadyExist(user.getLogin()))
            throw new UserLoginAlreadyExistException("Login already exist");
        return true;
    }

    private static boolean isPasswordLongEnough(String password) {
        return password.length() >= MIN_LENGTH_PASSWORD;
    }

    private static boolean isNameLongEnough(String login) {
        return login.length() >= MIN_LENGTH_LOGIN;
    }

    private static boolean isLoginAlreadyExist(String login) {
        User user = null;
        try {
            user = userDao.getUserByLogin(login);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (user == null) return false;

        return true;
    }
}