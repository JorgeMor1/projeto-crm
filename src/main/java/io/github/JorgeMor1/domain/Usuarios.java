package io.github.JorgeMor1.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "usuario")
public class Usuarios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String nome;
    private String sobrenome;
    private String email;
    @ManyToOne
    @JoinColumn(name = "cargo_id")
    private Cargos cargo;

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Cargos getCargo() {
        return cargo;
    }

    public void setCargo(Cargos cargo) {
        this.cargo = cargo;
    }
}
