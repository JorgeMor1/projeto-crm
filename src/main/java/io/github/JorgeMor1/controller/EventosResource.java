package io.github.JorgeMor1.controller;

import io.github.JorgeMor1.domain.Cliente;
import io.github.JorgeMor1.domain.Eventos;
import io.github.JorgeMor1.domain.StatusEventos;
import io.github.JorgeMor1.dto.ClienteDTO;
import io.github.JorgeMor1.dto.EventosDTO;
import io.github.JorgeMor1.repository.ClienteRepository;
import io.github.JorgeMor1.repository.EventosRepository;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("api/v1/eventos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EventosResource {


    private final ClienteRepository clienteRepository;

    private final EventosRepository eventosRepository;

    @Inject
    public EventosResource(ClienteRepository clienteRepository, EventosRepository eventosRepository) {
        this.clienteRepository = clienteRepository;
        this.eventosRepository = eventosRepository;
    }


    @POST
    @Transactional
    public Response createEvent(EventosDTO eventosDTO){
        Cliente cliente = clienteRepository.findById(eventosDTO.getClienteId());
        Eventos eventos = new Eventos();
        eventos.setCliente(cliente);
        eventosRepository.persist(eventos);
        return Response
                .status(Response.Status.CREATED.getStatusCode())
                .entity(eventosDTO)
                .build();

    }

    @GET
    public Response listAllEvents(){
        PanacheQuery<Eventos> query = eventosRepository.findAll();
        return Response.ok(query.list()).build();
    }

    /*Atualiza o status do evento, enviado o id do cliente como parâmetro

    * Necessário validar o id do usuário e o número do evento
     */

    @PUT
    @Path("{numero_evento}")
    @Transactional
    public Response updateStatusEvent(@PathParam("numero_evento") Integer numeroEvento, EventosDTO eventosDTO){
        /*Colocar aqui a ação de contato enviado, que será um envio de e-mail. Validar o id do usuário e o evento que será atualizado*/
        //Buscando o evento que está no banco pelo Id passado na URL;
        Eventos eventoExistente = eventosRepository.buscaPorNumeroEvento(numeroEvento);
        Cliente cliente = clienteRepository.findById(eventosDTO.getClienteId());
        eventoExistente.setCliente(cliente);
        eventoExistente.setStatusEvento(StatusEventos.ANDAMENTO);
        return Response.ok("Evento em Andamento").build();
    }

    //Um evento não pode ser excluído apenas mudado seu status
}
