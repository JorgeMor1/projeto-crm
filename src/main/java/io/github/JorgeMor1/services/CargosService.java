package io.github.JorgeMor1.services;

import io.github.JorgeMor1.domain.Cargos;
import io.github.JorgeMor1.dto.CargoDTO;
import io.github.JorgeMor1.exception.CargoNotFoundException;
import io.github.JorgeMor1.exception.CustomerNotFoundException;
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

    public Cargos updateposition(CargoDTO cargoDTO, Long idCargo){
        Cargos cargosId = cargoRepository.findByIdOptional(idCargo)
                .orElseThrow(() -> new CargoNotFoundException(idCargo));
        cargosId.setNomeCargo(cargoDTO.getNomeCargo().toUpperCase());
        return cargosId;
    }

    public void deletePosition(Long id){
        Cargos cargoId = cargoRepository.findByIdOptional(id)
                        .orElseThrow(() -> new CargoNotFoundException(id));
        cargoRepository.delete(cargoId);
    }
}
