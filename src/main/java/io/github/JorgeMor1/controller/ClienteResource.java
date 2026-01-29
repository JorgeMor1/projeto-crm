package io.github.JorgeMor1.controller;

import io.github.JorgeMor1.domain.Cliente;
import io.github.JorgeMor1.dto.ClienteDTO;
import io.github.JorgeMor1.repository.ClienteRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/clientes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ClienteResource {

    private final ClienteRepository clienteRepository;

    @Inject
    public ClienteResource(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @POST
    @Transactional
    public Response createClient(ClienteDTO clienteDTO){
        Cliente cliente = new Cliente();
        cliente.setNome(clienteDTO.getNome());
        cliente.setCpf(clienteDTO.getCpf());
        cliente.setTelefoneContato(clienteDTO.getTelefoneContato());
        cliente.setEmail(clienteDTO.getEmail());
        return Response.ok().build();

    }
}
