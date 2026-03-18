package io.github.JorgeMor1.exception;

public class ConflictException extends BusinessException {
    public ConflictException(String resource, Object id) {
        super(resource + " existente: " + id);
    }
}
