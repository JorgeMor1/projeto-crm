package io.github.JorgeMor1.exception;

public class BadRequestException extends BusinessException{
    public BadRequestException(String resource, Object id) {
        super(resource + " inválido: " + id);
    }
}
