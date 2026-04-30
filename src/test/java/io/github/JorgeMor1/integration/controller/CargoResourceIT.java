package io.github.JorgeMor1.integration.controller;

import io.github.JorgeMor1.domain.Cargos;
import io.github.JorgeMor1.dto.CargoDTO;
import io.github.JorgeMor1.exception.ConflictException;
import io.github.JorgeMor1.repository.CargoRepository;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.*;


@QuarkusTest
class CargoResourceIT {

    @Inject
    CargoRepository cargoRepository;

    @BeforeEach
    @Transactional
    void setUp() {
        cargoRepository.deleteAll();
    }

    private CargoDTO criaCargoDTO(String nome){
        CargoDTO dto = new CargoDTO();
        dto.setNomeCargo(nome);
                return dto;
    }



    @Test
    void shouldCreateCargoSuccessfully() {
         CargoDTO dto = criaCargoDTO("Desenvolvedor");

        given()
                .contentType(ContentType.JSON)
                .body(dto)
                .when()
                .post("/api/v1/cargos")
                .then()
                .statusCode(201)
                .body("nomeCargo", is("DESENVOLVEDOR"));

        Cargos cargoSalvo = cargoRepository.find("nomeCargo", "DESENVOLVEDOR").firstResult();

        assertNotNull(cargoSalvo);
        assertEquals("DESENVOLVEDOR", cargoSalvo.getNomeCargo());
    }

    @Test
    void shouldReturn409WhenTryingToCreateDuplicateCargo() {
        CargoDTO dto = criaCargoDTO("Desenvolvedor");
        given()
                .contentType(ContentType.JSON)
                .body(dto)
        .when()
                .post("/api/v1/cargos")
        .then()
                .statusCode(201);
        given()
                .contentType(ContentType.JSON)
                .body(dto)
                .when()
                .post("/api/v1/cargos")
                .then()
                .statusCode(409);

        List<Cargos> cargos = cargoRepository.list("nomeCargo", "DESENVOLVEDOR");

        assertEquals(1, cargos.size());


    }

    @Test
    void shouldUpdatedCargoSuccessfully(){
        CargoDTO dto = criaCargoDTO("Desenvolvedor");
        Long id =
        given()
                .contentType(ContentType.JSON)
                .body(dto)
                .when()
                .post("/api/v1/cargos")
                .then()
                .statusCode(201)
                .extract()
                .jsonPath()
                .getLong("idCargo");
        //atualizar o cargo
        CargoDTO dtoUpdate = criaCargoDTO("NOVO VENDEDOR");

        given()
                .contentType(ContentType.JSON)
                .body(dtoUpdate)
                .when()
                .put("/api/v1/cargos/"+ id)
                .then()
                .statusCode(200);
        Cargos cargoAtualizado = cargoRepository.findById(id);

        assertNotNull(cargoAtualizado);
        assertEquals("NOVO VENDEDOR", cargoAtualizado.getNomeCargo());

    }



    /*@Test
    void listAllCargos() {
    }*/

/*    @Test
    void listPositionById() {
    }*/

    /*@Test
    void updatePosition() {
    }*/

    /*@Test
    void deletePosition() {
    }*/
}