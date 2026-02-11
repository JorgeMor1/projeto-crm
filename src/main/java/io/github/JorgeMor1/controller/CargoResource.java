package io.github.JorgeMor1.controller;

import io.github.JorgeMor1.dto.CargoDTO;
import io.github.JorgeMor1.repository.CargoRepository;
import io.github.JorgeMor1.services.CargosService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("api/v1/cargos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CargoResource {

    private final CargoRepository cargoRepository;

    private final CargosService cargosService;

    @Inject
    public CargoResource(CargoRepository cargoRepository, CargosService cargosService) {
        this.cargoRepository = cargoRepository;
        this.cargosService = cargosService;
    }

    @POST
    @Transactional
    public Response createCargo(CargoDTO cargoDTO){
        cargosService.createPosition(cargoDTO);
        return Response
                .status(Response.Status.CREATED.getStatusCode())
                .entity(cargoDTO)
                .build();
    }

}
