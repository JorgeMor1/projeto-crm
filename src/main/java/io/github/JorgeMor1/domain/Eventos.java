package io.github.JorgeMor1.domain;

import jakarta.persistence.*;
import jdk.jshell.Snippet;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.JdbcType;
import org.hibernate.dialect.type.PostgreSQLEnumJdbcType;

@Table(name = "eventos")
@Entity
public class Eventos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero_evento", insertable = false, updatable = false)
    @Generated
    private Integer numeroEvento;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @Enumerated(EnumType.STRING)
    @JdbcType(PostgreSQLEnumJdbcType.class)

    @Column(name = "status_evento", columnDefinition = "status_eventos_enum")
    private StatusEventos statusEvento = StatusEventos.AGUARDANDO;



    public Eventos() {
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public StatusEventos getStatusEvento() {
        return statusEvento;
    }

    public void setStatusEvento(StatusEventos statusEvento) {
        this.statusEvento = statusEvento;
    }

    public Long getId() {
        return id;
    }

    public Integer getNumeroEvento() {
        return numeroEvento;
    }
}

