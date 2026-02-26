package io.github.JorgeMor1.exception;

public class CargoNotFoundException extends RuntimeException {
    public CargoNotFoundException(Long idCargo) {
        super("Cargo não encontrado");
    }
}
