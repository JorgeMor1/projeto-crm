package io.github.JorgeMor1.dto;

import io.github.JorgeMor1.domain.Usuarios;

public class UsuariosResponseDTO {
    private String login;
    private String nome;
    private String sobrenome;
    private String email;
    private String nomeCargo;

    public UsuariosResponseDTO(String login, String nome, String sobrenome, String email, String nomeCargo) {
        this.login = login;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.nomeCargo = nomeCargo;
    }

    public UsuariosResponseDTO() {
    }

    public UsuariosResponseDTO(Usuarios usuarios) {
        this.login = usuarios.getLogin();
        this.nome = usuarios.getNome();
        this.sobrenome = usuarios.getSobrenome();
        this.email = usuarios.getEmail();
        this.nomeCargo = usuarios.getCargo().getNomeCargo().toUpperCase();
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
        this.nomeCargo = nomeCargo;
    }
}
