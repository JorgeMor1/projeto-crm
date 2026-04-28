package io.github.JorgeMor1.integration.controller;

import io.github.JorgeMor1.domain.Cargos;
import io.github.JorgeMor1.dto.CargoDTO;
import io.github.JorgeMor1.repository.CargoRepository;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@QuarkusTest
class CargoResourceIT {

    @Inject
    CargoRepository cargoRepository;

    @BeforeEach
    @Transactional
    void setUp() {
        cargoRepository.deleteAll();
    }

    @Test
    void shouldCreateCargoSuccessfully() {
        CargoDTO dto = new CargoDTO();
        dto.setNomeCargo("Desenvolvedor");


        given()
                .contentType(ContentType.JSON)
                .body(dto)
        .when()
                .post("/api/v1/cargos")
        .then()
                    .statusCode(201);
                    //.body("nomeCargo", is("DESENVOLVEDOR"));
        Cargos cargoSalvo = cargoRepository.find("nomeCargo", "DESENVOLVEDOR").firstResult();

        assertNotNull(cargoSalvo);
        assertEquals("DESENVOLVEDOR", cargoSalvo.getNomeCargo());
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