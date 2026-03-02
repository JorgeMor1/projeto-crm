package io.github.JorgeMor1.services;

import io.github.JorgeMor1.domain.Cargos;
import io.github.JorgeMor1.dto.CargoDTO;
import io.github.JorgeMor1.dto.CargoResponseDTO;
import io.github.JorgeMor1.exception.CargoNotFoundException;
import io.github.JorgeMor1.exception.CustomerNotFoundException;
import io.github.JorgeMor1.repository.CargoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.WebApplicationException;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class CargosService {

    @Inject
    CargoRepository cargoRepository;

    public Cargos createPosition(CargoDTO cargoDTO){
        String cargoFormatado = cargoDTO.getNomeCargo().toUpperCase();

        Optional<Cargos> cargoExistente = cargoRepository.find("nomeCargo", cargoFormatado).firstResultOptional();
        if (cargoExistente.isPresent()) {
            throw new WebApplicationException("Já existe um cargo cadastrado com o nome: " + cargoFormatado, 409);
        }
        Cargos cargo = new Cargos();
        cargo.setNomeCargo(cargoDTO.getNomeCargo().toUpperCase());
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

    public List<Cargos> listAll(){
        return cargoRepository.listAll();
    }
}
