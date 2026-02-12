package io.github.JorgeMor1.controller;

import io.github.JorgeMor1.domain.Usuarios;
import io.github.JorgeMor1.dto.UsuariosDTO;
import io.github.JorgeMor1.repository.UsuarioRepository;
import io.github.JorgeMor1.services.ClienteService;
import io.github.JorgeMor1.services.UsuariosService;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import jakarta.inject.Inject;
import jakarta.persistence.Id;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("api/v1/usuarios")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UsuariosResource {

    private final UsuarioRepository usuarioRepository;

    private final UsuariosService usuariosService;

    @Inject
    public UsuariosResource(UsuarioRepository usuarioRepository, UsuariosService usuariosService) {
        this.usuarioRepository = usuarioRepository;
        this.usuariosService = usuariosService;
    }

    @POST
    @Transactional
    public Response createUser(UsuariosDTO usuariosDTO){
        usuariosService.createUser(usuariosDTO);
        return  Response
                .status(Response.Status.CREATED)
                .entity(usuariosDTO)
                .build();
    }

    @PUT
    @Transactional
    @Path("{id}")
    public Response updateUser(@PathParam("id") Long id, UsuariosDTO usuariosDTO){
        usuariosService.updateUser(usuariosDTO, id);
        return Response.ok().build();
    }

    @GET
    public Response listAllUsers(){
        PanacheQuery<Usuarios> allUsers = usuarioRepository.findAll();
        return Response.ok(allUsers.list()).build();
    }

    @DELETE
    @Transactional
    @Path("{id}")
    public Response deleteUser(@PathParam("id") Long userId){
        usuariosService.deleteUser(userId);
        return Response.ok().build();
    }
}
