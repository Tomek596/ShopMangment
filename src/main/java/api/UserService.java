package api;

import entity.User;

import java.io.IOException;
import java.util.List;

public interface UserService {
    boolean addUser(User user);

    void removeUserById(Long userId) throws IOException;

    List<User> getAllUsers() throws IOException;
    User getUserById(Long userId) throws IOException;
    User getUserByLogin(String login) throws IOException;

    boolean isCorrectLoginAndPassword(String login, String password);
}
