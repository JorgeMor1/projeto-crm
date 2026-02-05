package io.github.JorgeMor1.exception;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException (Long idCliente){
        super("Cliente não encontrado " + idCliente);
    }
}
