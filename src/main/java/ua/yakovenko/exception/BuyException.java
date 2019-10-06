package ua.yakovenko.exception;

public class BuyException extends Exception {
    public BuyException() {}

    public BuyException(String message) {
        super(message);
    }

    public BuyException(String message, Throwable cause) {
        super(message, cause);
    }
}
