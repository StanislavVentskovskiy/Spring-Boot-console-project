package ua.com.foxminded.exception;

public class DAOException extends RuntimeException {

    public DAOException(Throwable error) {

        super("Database error.", error);
    }
}
