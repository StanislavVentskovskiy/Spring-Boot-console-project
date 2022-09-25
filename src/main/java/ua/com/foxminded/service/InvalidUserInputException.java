package ua.com.foxminded.service;

public class InvalidUserInputException extends Exception {
    public InvalidUserInputException() {

        super("Invalid console input.");
    }
}
