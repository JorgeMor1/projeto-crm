package io.github.JorgeMor1.services;

import io.github.JorgeMor1.domain.Cargos;
import io.github.JorgeMor1.dto.CargoDTO;
import io.github.JorgeMor1.exception.CargoNotFoundException;
import io.github.JorgeMor1.exception.ResourceNotFoundException;
import io.github.JorgeMor1.repository.CargoRepository;
import io.quarkus.panache.common.Page;
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
                .orElseThrow(() -> new ResourceNotFoundException("Cargo",idCargo));
        cargosId.setNomeCargo(cargoDTO.getNomeCargo().toUpperCase());
        return cargosId;
    }

    public void deletePosition(Long idCargo){
        Cargos cargoId = cargoRepository.findByIdOptional(idCargo)
                        .orElseThrow(() -> new ResourceNotFoundException("Cargo",idCargo));
        cargoRepository.delete(cargoId);
    }

    public List<Cargos> listAll(int page, int size){
        return cargoRepository.findAll().page(Page.of(page, size)).list();
    }
}
