package ua.com.foxminded.formatter.dao;

public class DAOException extends RuntimeException {

    public DAOException(Throwable error) {

        super("Database error.", error);
    }
}
