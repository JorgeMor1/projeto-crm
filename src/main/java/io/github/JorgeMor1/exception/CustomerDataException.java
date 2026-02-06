package io.github.JorgeMor1.exception;

public class CustomerDataException extends RuntimeException {
    public CustomerDataException(String message, String data) {
        super(message + data);
    }

}
