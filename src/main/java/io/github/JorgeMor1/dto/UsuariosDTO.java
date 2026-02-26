package io.github.JorgeMor1.dto;

import io.github.JorgeMor1.domain.Cargos;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class UsuariosDTO {
    private String login;
    private String nome;
    private String sobrenome;
    private String email;
    private String nomeCargo;

    public UsuariosDTO(String login, String nome, String sobrenome, String email, String nomeCargo) {
        this.login = login;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.nomeCargo = nomeCargo;
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

    public String getNomeCargo() {
        return nomeCargo;
    }

    public void setNomeCargo(String nomeCargo) {
        nomeCargo = nomeCargo;
    }
}
