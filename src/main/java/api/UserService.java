package api;

import entity.User;
import exceptions.UserLoginAlreadyExistException;
import exceptions.UserShortLengthLoginException;
import exceptions.UserShortLengthPasswordException;

import java.util.List;

public interface UserService {
    boolean addUser(User user) throws UserLoginAlreadyExistException, UserShortLengthLoginException, UserShortLengthPasswordException;

    void removeUserById(Long userId);

    List<User> getAllUsers();

    User getUserById(Long userId);

    User getUserByLogin(String login);

    boolean isCorrectLoginAndPassword(String login, String password);
}
