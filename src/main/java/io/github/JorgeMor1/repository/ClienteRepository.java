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

    public boolean buscaCpf(String cpf) {
         return  find("cpf", cpf).firstResultOptional().isPresent();

    }

    public boolean buscaEmail(String email) {
        return find("email", email).firstResultOptional().isPresent();
    }

    public boolean buscaTelefone(String telefone) {
        return find("telefone", telefone).firstResultOptional().isPresent();
    }
}
