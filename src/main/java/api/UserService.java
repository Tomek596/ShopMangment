package api;

import entity.User;
import exceptions.UserLoginAlreadyExistException;
import exceptions.UserShortLengthLoginException;
import exceptions.UserShortLengthPasswordException;

import java.io.IOException;

public interface UserService {

    void addUser(User user) throws IOException, UserShortLengthPasswordException, UserLoginAlreadyExistException, UserShortLengthLoginException;

    void removeUserById(Long userId) throws IOException;
}
