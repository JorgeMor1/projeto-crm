package io.github.JorgeMor1.repository;

import io.github.JorgeMor1.domain.Cliente;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped
public class ClienteRepository implements PanacheRepository<Cliente> {

    public Optional<Cliente> buscarClientePorId(Long idCliente){
        return find("id", idCliente).firstResultOptional();
    }
}
