package io.github.JorgeMor1.controller;

import io.github.JorgeMor1.domain.Cliente;
import io.github.JorgeMor1.dto.ClientResponseDTO;
import io.github.JorgeMor1.dto.ClienteDTO;
import io.github.JorgeMor1.repository.ClienteRepository;
import io.github.JorgeMor1.services.ClienteService;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("api/v1/clientes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ClienteResource {

    private final ClienteRepository clienteRepository;

    private final ClienteService clientService;

    @Inject
    public ClienteResource(ClienteRepository clienteRepository, ClienteService clientService) {
        this.clienteRepository = clienteRepository;
        this.clientService = clientService;
    }

    @POST
    @Transactional
    public Response createClient(ClienteDTO clienteDTO){
        Cliente cliente = clientService.createClient(clienteDTO);
        ClientResponseDTO clientResponseDTO = new ClientResponseDTO(cliente);
        return Response
                .status(Response.Status.CREATED.getStatusCode())
                .entity(clientResponseDTO)
                .build();

    }

    @GET
    public Response listAllClients(){
        List<Cliente> cliente = clientService.listAllClients();
        List<ClientResponseDTO> response =
                cliente.stream().map(ClientResponseDTO::new).toList();

        return Response.ok(response).build();
    }

    @PUT
    @Path("{id}")
    @Transactional
    public Response updateClient(@PathParam("id") Long id, ClienteDTO clienteDTO){
        clientService.atualizaCliente(id, clienteDTO);
        return Response.ok().build();
    }


    @DELETE
    @Path("{id}")
    @Transactional
    public Response deleteClient(@PathParam("id") Long id){
      clientService.deletaCliente(id);
        return Response.ok().build();
    }
}
