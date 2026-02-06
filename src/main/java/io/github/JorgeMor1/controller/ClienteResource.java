package io.github.JorgeMor1.controller;

import io.github.JorgeMor1.domain.Cliente;
import io.github.JorgeMor1.dto.ClienteDTO;
import io.github.JorgeMor1.repository.ClienteRepository;
import io.github.JorgeMor1.services.ClientService;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("api/v1/clientes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ClienteResource {

    private final ClienteRepository clienteRepository;

    private final ClientService clientService;

    @Inject
    public ClienteResource(ClienteRepository clienteRepository, ClientService clientService) {
        this.clienteRepository = clienteRepository;
        this.clientService = clientService;
    }

    @POST
    @Transactional
    public Response createClient(ClienteDTO clienteDTO){
        clientService.createClient(clienteDTO);
        return Response
                .status(Response.Status.CREATED.getStatusCode())
                .entity(clienteDTO)
                .build();

    }

    @GET
    public Response listAllClients(){
        PanacheQuery<Cliente> query = clienteRepository.findAll();
        return Response.ok(query.list()).build();
    }

    @PUT
    @Path("{id}")
    @Transactional
    public Response updateClient(@PathParam("id") Long id, ClienteDTO clienteDTO){
        Cliente cliente = clienteRepository.findById(id);
        cliente.setNome(clienteDTO.getNome());
        cliente.setCpf(clienteDTO.getCpf());
        cliente.setTelefoneContato(clienteDTO.getTelefoneContato());
        cliente.setEmail(clienteDTO.getEmail());
        return Response.ok().build();
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response deleteClient(@PathParam("id") Long id){
        Cliente cliente = clienteRepository.findById(id);
        clienteRepository.delete(cliente);
        return Response.ok().build();
    }
}
