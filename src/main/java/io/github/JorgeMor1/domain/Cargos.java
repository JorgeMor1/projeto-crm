package io.github.JorgeMor1.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "cargo")
public class Cargos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cargo_id;
    @Column(name = "nome_cargo", unique = true)
    private String nomeCargo;

    @Column(name = "created_at", nullable = false, updatable = false)
    public LocalDateTime createdAt;

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now().withNano(0);
    }

    public String getNomeCargo() {
        return nomeCargo;
    }

    public void setNomeCargo(String nomeCargo) {
        this.nomeCargo = nomeCargo;
    }

    public Long getCargo_id() {
        return cargo_id;
    }
}
