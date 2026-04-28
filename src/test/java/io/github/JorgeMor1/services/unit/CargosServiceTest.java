package io.github.JorgeMor1.services.unit;

import io.github.JorgeMor1.domain.Cargos;
import io.github.JorgeMor1.dto.CargoDTO;
import io.github.JorgeMor1.exception.ConflictException;
import io.github.JorgeMor1.exception.ResourceNotFoundException;
import io.github.JorgeMor1.repository.CargoRepository;
import io.github.JorgeMor1.services.CargosService;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class CargosServiceTest {

    @InjectMock
    CargoRepository cargoRepository;

    @Inject
    CargosService cargosService;

    private CargoDTO cargoDTO;
    private Cargos cargoExistente;
    private Long cargoId;

    @BeforeEach
    void setUp() {
         cargoDTO = new CargoDTO();
         cargoDTO.setNomeCargo("Vendedor Teste22");
         cargoExistente = new Cargos();
         cargoId = 1L;
    }

    @Test
    void createPosition_ShouldReturnCargo_WhenCargoDoesNotExist() {
        PanacheQuery query = Mockito.mock(PanacheQuery.class);
        Mockito.when(cargoRepository.find("nomeCargo", "VENDEDOR TESTE22"))
                .thenReturn(query);
        Mockito.when(query.firstResultOptional())
                .thenReturn(Optional.empty());
        Cargos resultado = cargosService.createPosition(cargoDTO);

        assertEquals("VENDEDOR TESTE22", resultado.getNomeCargo());
    }

    @Test
    void createPosition_SholdThrowException_WhenCargoAlreadyExists(){
        PanacheQuery query = Mockito.mock(PanacheQuery.class);

        Mockito.when(cargoRepository.find("nomeCargo", "VENDEDOR TESTE22"))
                .thenReturn(query);
        Mockito.when(query.firstResultOptional())
                .thenReturn(Optional.of(cargoExistente));

        assertThrows(ConflictException.class, () -> {
            cargosService.createPosition(cargoDTO);
        });
    }

    @Test
    void updatePosition_ShouldUpdate_WhenCargoAlreadyExists() {
        cargoDTO.setNomeCargo("Novo Cargo vindo do DTO");

        Mockito.when(cargoRepository.findByIdOptional(cargoId))
                .thenReturn(Optional.of(cargoExistente));

        Cargos resultado = cargosService.updateposition(cargoDTO, cargoId);

        assertNotNull(resultado);
        assertEquals("NOVO CARGO VINDO DO DTO", resultado.getNomeCargo());
    }

    @Test
    void updatePosition_ShouldThrowException_WhenCargoDoesNotExist(){

        Mockito.when(cargoRepository.findByIdOptional(cargoId))
                .thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () ->{
           cargosService.updateposition(cargoDTO,cargoId);
        });

        Mockito.verify(cargoRepository, Mockito.never()).persist(Mockito.any(Cargos.class));
    }

    @Test
    void deletePosition_ShouldDelete_WhenCargoExist() {

        Mockito.when(cargoRepository.findByIdOptional(cargoId))
                .thenReturn(Optional.of(cargoExistente));

         cargosService.deletePosition(cargoId);

        Mockito.verify(cargoRepository, Mockito.times(1)).delete(cargoExistente);
    }


    @Test
    void deletePosition_ShouldThrowException_WhenCargoDoesNotExist(){
        Long idInexistente = 99L;

        Mockito.when(cargoRepository.findByIdOptional(idInexistente))
                .thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () ->{
            cargosService.deletePosition(idInexistente);
        });
        Mockito.verify(cargoRepository, Mockito.never()).delete(Mockito.any());

    }



    @Test
    void listPosition_ShouldReturnPosition_WhenIdExists() {
        cargoExistente.setNomeCargo("Vendedor Teste22");

        Mockito.when(cargoRepository.findByIdOptional(cargoId))
                .thenReturn(Optional.of(cargoExistente));

        Cargos resultado = cargosService.listPositionById(cargoId);

        assertNotNull(resultado);
        assertEquals("Vendedor Teste22", resultado.getNomeCargo());
        Mockito.verify(cargoRepository).findByIdOptional(cargoId);

    }

    @Test
    void listPosition_SholdThrowException_WhenCargoDoesNotExist(){
        Mockito.when(cargoRepository.findByIdOptional(cargoId))
                .thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            cargosService.listPositionById(cargoId);

        });
        Mockito.verify(cargoRepository).findByIdOptional(cargoId);
    }
}