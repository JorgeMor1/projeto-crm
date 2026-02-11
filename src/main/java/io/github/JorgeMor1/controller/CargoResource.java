package io.github.JorgeMor1.controller;

import io.github.JorgeMor1.domain.Cargos;
import io.github.JorgeMor1.dto.CargoDTO;
import io.github.JorgeMor1.repository.CargoRepository;
import io.github.JorgeMor1.services.CargosService;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.hibernate.query.Query;

import java.util.List;

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

    @GET
    public Response listAllCargos(){
        PanacheQuery<Cargos> cargos = cargoRepository.findAll();
        return Response.ok(cargos.list()).build();
    }

    @PUT
    @Path("{id}")
    @Transactional
    public Response updatePosition(@PathParam("id") Long id, CargoDTO cargoDTO){
        cargosService.updateposition(cargoDTO, id);
        return Response.ok().build();

    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response deletePosition(@PathParam("id") Long id){
        cargosService.deletePosition(id);
        return Response.ok().build();
    }

}
