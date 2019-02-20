package service;

import api.UserDao;
import api.UserService;
import dao.UserDaoImpl;
import entity.User;
import exceptions.UserLoginAlreadyExistException;
import exceptions.UserShortLengthLoginException;
import exceptions.UserShortLengthPasswordException;
import validator.UserValidator;

import java.io.IOException;
import java.util.List;

public class UserServiceImpl implements UserService {
    private static UserServiceImpl instance = null;
    private UserDao userDao = UserDaoImpl.getInstance();
    private UserValidator userValidator = UserValidator.getInstance();

    public UserServiceImpl() {
    }

    public static UserServiceImpl getInstance() {
        if (instance == null) {
            instance = new UserServiceImpl();
        }
        return instance;
    }

    public List<User> getAllUsers() throws IOException {
        return userDao.getAllUsers();
    }

    @Override
    public void addUser(User user) throws IOException, UserShortLengthPasswordException, UserLoginAlreadyExistException, UserShortLengthLoginException {
        if (userValidator.isValidate(user)) {
            userDao.saveUser(user);
        }
    }

    @Override
    public void removeUserById(Long userId) throws IOException {
        userDao.removeUserById(userId);
    }
}