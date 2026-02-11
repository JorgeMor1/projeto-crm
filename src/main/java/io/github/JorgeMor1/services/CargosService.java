package io.github.JorgeMor1.services;

import io.github.JorgeMor1.domain.Cargos;
import io.github.JorgeMor1.dto.CargoDTO;
import io.github.JorgeMor1.repository.CargoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
@ApplicationScoped
public class CargosService {

    @Inject
    CargoRepository cargoRepository = new CargoRepository();

    public Cargos createPosition(CargoDTO cargoDTO){
        Cargos cargo = new Cargos();
        String cargoFormatado = cargoDTO.getNomeCargo().toUpperCase();
        cargo.setNomeCargo(cargoFormatado);
        cargoRepository.persist(cargo);
        return cargo;
    }
}
