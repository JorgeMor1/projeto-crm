package io.github.JorgeMor1.controller;

import io.github.JorgeMor1.domain.Eventos;
import io.github.JorgeMor1.dto.EventosDTO;
import io.github.JorgeMor1.dto.EventosResponseDTO;
import io.github.JorgeMor1.repository.ClienteRepository;
import io.github.JorgeMor1.repository.EventosRepository;
import io.github.JorgeMor1.services.ClienteService;
import io.github.JorgeMor1.services.EventService;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Optional;

@Path("api/v1/eventos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EventosResource {


    private final ClienteRepository clienteRepository;

    private final EventosRepository eventosRepository;

    private final EventService eventService;

    private final ClienteService clientService;

    @Inject
    public EventosResource(ClienteRepository clienteRepository, EventosRepository eventosRepository, EventService eventService, ClienteService clientService) {
        this.clienteRepository = clienteRepository;
        this.eventosRepository = eventosRepository;
        this.eventService = eventService;
        this.clientService = clientService;
    }


    @POST
    @Transactional
    public Response createEvent(EventosDTO eventosDTO){
        Eventos eventos = eventService.criaEventos(eventosDTO.getClienteId(), eventosDTO);
        EventosResponseDTO responseDTO = new EventosResponseDTO(eventos);

        return Response
                .status(Response.Status.CREATED)
                .entity(responseDTO)
                .build();

    }

    @GET
    public Response listAllEvents(
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("size") @DefaultValue("10") int size
    ){
        List<EventosResponseDTO> eventos =
                eventService.listAllEventos(page, size)
                        .stream()
                        .map(EventosResponseDTO::new)
                        .toList();
        return Response.ok(eventos).build();
    }

    /*Atualiza o status do evento, enviado o id do cliente como parâmetro

    * Necessário validar o id do usuário e o número do evento
     */

    @PUT
    @Path("{numero_evento}")
    @Transactional
    public Response updateStatusEvent(@PathParam("numero_evento") Integer numeroEvento,  EventosDTO eventosDTO){
        eventService.buscarEventosOuFalhar(numeroEvento, eventosDTO);
        return Response.ok().build();
    }

    //Um evento não pode ser excluído apenas mudado seu status
}
