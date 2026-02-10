package io.github.JorgeMor1.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "cargo")
public class Cargos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome_cargo")
    private String nomeCargo;

    public String getNomeCargo() {
        return nomeCargo;
    }

    public void setNomeCargo(String nomeCargo) {
        this.nomeCargo = nomeCargo;
    }

    public Long getId() {
        return id;
    }
}
