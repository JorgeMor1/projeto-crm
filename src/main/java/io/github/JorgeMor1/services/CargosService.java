package io.github.JorgeMor1.services;

import io.github.JorgeMor1.domain.Cargos;
import io.github.JorgeMor1.repository.CargoRepository;
import jakarta.inject.Inject;

public class CargosService {

    @Inject
    CargoRepository cargoRepository = new CargoRepository();

    public Cargos createPosition(Car){

    }
}
