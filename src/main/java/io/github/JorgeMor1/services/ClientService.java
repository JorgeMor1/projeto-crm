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

import java.util.Optional;

@ApplicationScoped
public class ClientService {
    @Inject
    ClienteRepository clienteRepository = new ClienteRepository();


    public Cliente buscarClienteOuFalhar (Long idCliente){
        return clienteRepository.buscarClientePorId(idCliente)
                .orElseThrow(() -> new CustomerNotFoundException(idCliente));
    }
}
