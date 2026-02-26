package io.github.JorgeMor1.services;

import io.github.JorgeMor1.domain.Cliente;
import io.github.JorgeMor1.domain.Eventos;
import io.github.JorgeMor1.domain.StatusEventos;
import io.github.JorgeMor1.domain.Usuarios;
import io.github.JorgeMor1.dto.EventosDTO;
import io.github.JorgeMor1.dto.EventosResponseDTO;
import io.github.JorgeMor1.exception.CustomerNotFoundException;
import io.github.JorgeMor1.exception.EventNotFoundException;
import io.github.JorgeMor1.repository.ClienteRepository;
import io.github.JorgeMor1.repository.EventosRepository;
import io.github.JorgeMor1.repository.UsuarioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class EventService {

    @Inject
     EventosRepository eventosRepository = new EventosRepository();
    @Inject
    ClienteRepository clienteRepository = new ClienteRepository();
    @Inject
    UsuarioRepository usuarioRepository = new UsuarioRepository();

    public Eventos buscaClienteId(Long idCliente){
        return eventosRepository.findByIdOptional(idCliente)
                .orElseThrow(() -> new CustomerNotFoundException(idCliente));

    }


    public EventosResponseDTO criaEventos(Long idCliente, EventosDTO eventosDTO){
        //Usuarios usuario = usuarioRepository.find("login", eventosDTO.getUsuarioLogin()).firstResult();
        Usuarios usuario = usuarioRepository.findById(eventosDTO.getUsuarioId());
        //EventosResponseDTO eventosResponseDTO = new EventosResponseDTO();
        Cliente cliente = clienteRepository.findByIdOptional(idCliente)
                .orElseThrow(() -> new CustomerNotFoundException(idCliente));
                        Eventos evento = new Eventos();
                        evento.setCliente(cliente);
                        evento.setOrigem(eventosDTO.getOrigem().toUpperCase());
                        evento.setUsuario(usuario);
                        evento.setStatusEvento(StatusEventos.ANDAMENTO);
                        eventosRepository.persist(evento);
                return new EventosResponseDTO(evento.getId(), evento.getCliente().getId(), evento.getUsuario().getId(), evento.getOrigem(), evento.getStatusEvento().name(), evento.getCreatedAt());
    }



    //Esse método está no PUT, deveria estar no GET para buscar o evento
    public Eventos buscarEventosOuFalhar(Integer numeroEvento){
        return eventosRepository.criandoEventoEStatusDefault(numeroEvento)
                .orElseThrow(() -> new EventNotFoundException(numeroEvento));
    }

    public List<EventosResponseDTO> listAll() {

        return eventosRepository.listAll()
                .stream()
                .map(evento -> new EventosResponseDTO(
                        evento.getId(),
                        evento.getCliente().getId(),
                        evento.getUsuario().getId(),
                        evento.getOrigem(),
                        evento.getStatusEvento().name(),
                        evento.getCreatedAt()
                ))
                .toList();
    }
}
