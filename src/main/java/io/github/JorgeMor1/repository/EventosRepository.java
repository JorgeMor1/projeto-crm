package io.github.JorgeMor1.repository;

import io.github.JorgeMor1.domain.Eventos;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import javax.management.Query;
import java.util.List;

@ApplicationScoped
public class EventosRepository implements PanacheRepository<Eventos> {


    public Eventos buscaPorNumeroEvento(Integer numero) {
        return find("numeroEvento", numero).firstResult();
    }
}
