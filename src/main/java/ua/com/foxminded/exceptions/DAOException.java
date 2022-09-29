package ua.com.foxminded.exceptions;

public class DAOException extends RuntimeException {
    public DAOException(Throwable error) {

        super("Database error.", error);
    }
}
