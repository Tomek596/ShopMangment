package facade;

import api.UserRegisterLoginFacade;
import api.UserService;
import entity.User;
import exceptions.UserLoginAlreadyExistException;
import exceptions.UserShortLengthLoginException;
import exceptions.UserShortLengthPasswordException;
import service.UserServiceImpl;

public class UserRegisterLoginFacadeImpl implements UserRegisterLoginFacade {
    private UserService userService = UserServiceImpl.getInstance();
    private static UserRegisterLoginFacade instance = null;

    private UserRegisterLoginFacadeImpl() {

    }

    public static UserRegisterLoginFacade getInstance() {
        if (instance == null) {
            instance = new UserRegisterLoginFacadeImpl();
        }
        return instance;
    }

    public String registerUser(User user) {
        try {
            userService.addUser(user);
            return "Register successfully";
        } catch (UserShortLengthPasswordException e) {
            e.printStackTrace();
            return e.getMessage();
        } catch (UserLoginAlreadyExistException e) {
            e.printStackTrace();
            return e.getMessage();
        } catch (UserShortLengthLoginException e) {
            e.printStackTrace();
            return e.getMessage();
        }

    }

    public boolean loginUser(String login, String password) {
        return userService.isCorrectLoginAndPassword(login, password);
    }
}
