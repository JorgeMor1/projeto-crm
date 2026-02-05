package io.github.JorgeMor1.services;

import io.github.JorgeMor1.domain.Cliente;
import io.github.JorgeMor1.domain.Eventos;
import io.github.JorgeMor1.domain.StatusEventos;
import io.github.JorgeMor1.exception.CustomerNotFoundException;
import io.github.JorgeMor1.exception.EventNotFoundException;
import io.github.JorgeMor1.repository.ClienteRepository;
import io.github.JorgeMor1.repository.EventosRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class EventService {

    @Inject
     EventosRepository eventosRepository = new EventosRepository();
    @Inject
    ClienteRepository clienteRepository = new ClienteRepository();

    public Eventos buscaClienteId(Long idCliente){
        return eventosRepository.findByIdOptional(idCliente)
                .orElseThrow(() -> new CustomerNotFoundException(idCliente));

    }


    //Criar uma classe para ResponseDTO e devolver aqui
    public Eventos criaEventos(Long idCliente){
        Cliente cliente = clienteRepository.findByIdOptional(idCliente)
                .orElseThrow(() -> new CustomerNotFoundException(idCliente));
                        Eventos evento = new Eventos();
                        evento.setCliente(cliente);
                        evento.setStatusEvento(StatusEventos.ANDAMENTO);
                        eventosRepository.persist(evento);
                        return evento;

    }



    //Esse método está no PUT, deveria estar no GET para buscar o evento
    public Eventos buscarEventosOuFalhar(Integer numeroEvento){
        return eventosRepository.criandoEventoEStatusDefault(numeroEvento)
                .orElseThrow(() -> new EventNotFoundException(numeroEvento));
    }
}
