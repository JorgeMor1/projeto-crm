package io.github.JorgeMor1.repository;

import io.github.JorgeMor1.domain.Usuarios;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UsuarioRepository implements PanacheRepository<Usuarios> {
    public boolean buscaCpf(String cpf) {
        return  find("cpf", cpf).firstResultOptional().isPresent();

    }

    public boolean buscaEmail(String email) {
        return find("email", email).firstResultOptional().isPresent();
    }
}
