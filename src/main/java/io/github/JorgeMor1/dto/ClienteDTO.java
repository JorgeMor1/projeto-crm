package io.github.JorgeMor1.dto;

public class ClienteDTO {
    private String nome;
    private String cpf;
    private String telefoneContato;
    private String email;

    public ClienteDTO(String nome, String cpf, String telefoneContato, String email) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefoneContato = telefoneContato;
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefoneContato() {
        return telefoneContato;
    }

    public void setTelefoneContato(String telefoneContato) {
        this.telefoneContato = telefoneContato;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
