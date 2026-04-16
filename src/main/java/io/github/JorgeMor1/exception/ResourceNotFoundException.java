package io.github.JorgeMor1.exception;

public class ResourceNotFoundException extends BusinessException {
    public ResourceNotFoundException(String resource, Object id) {
        super(resource + " não encontrado: " + id);
    }
}
