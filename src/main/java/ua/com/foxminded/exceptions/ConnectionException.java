package ua.com.foxminded.exceptions;

public class ConnectionException extends RuntimeException {
    public ConnectionException(Throwable error) {
        super("Connection to Database error.", error);
    }
}
