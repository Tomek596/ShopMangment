package exceptions;

public class UserShortLengthPasswordException extends Exception{

    public UserShortLengthPasswordException() {
    }

    public UserShortLengthPasswordException(String message) {
        super(message);
    }
}
