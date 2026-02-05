package io.github.JorgeMor1.exception;

public class EventNotFoundException extends RuntimeException{
    public EventNotFoundException(Integer numeroEvento) {
        super("Evento não encontrado: " + numeroEvento);
    }
}
