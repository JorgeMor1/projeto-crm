package io.github.JorgeMor1.repository;

import io.github.JorgeMor1.domain.Cliente;
import io.github.JorgeMor1.domain.Eventos;
import io.github.JorgeMor1.domain.StatusEventos;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import javax.management.Query;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class EventosRepository implements PanacheRepository<Eventos> {

    public void persist(Eventos evento) {
        this.persist(evento);
    }





    //Modificar esse método, ele não está criando o evento, apenas atualizando na criação do evento
    public Optional<Eventos> criandoEventoEStatusDefault(Integer numeroEvento) {
        update("statusEvento = ?1 WHERE numeroEvento = ?2", StatusEventos.ANDAMENTO, numeroEvento);
        return find("numeroEvento", numeroEvento).firstResultOptional();
    }
}
