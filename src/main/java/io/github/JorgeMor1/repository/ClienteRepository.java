package io.github.JorgeMor1.repository;

import io.github.JorgeMor1.domain.Cliente;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ClienteRepository implements PanacheRepository<Cliente> {
}
