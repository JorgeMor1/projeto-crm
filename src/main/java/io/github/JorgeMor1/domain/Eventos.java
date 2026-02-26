package io.github.JorgeMor1.domain;

import jakarta.persistence.*;
import jdk.jshell.Snippet;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.JdbcType;
import org.hibernate.dialect.type.PostgreSQLEnumJdbcType;

import java.time.LocalDateTime;

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

    private String origem;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuarios usuario;

    @Column(name = "created_at", nullable = false, updatable = false)
    public LocalDateTime createdAt;

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now().withNano(0);
    }



    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public Eventos() {
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
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

