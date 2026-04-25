package io.github.JorgeMor1.dto;

import io.github.JorgeMor1.domain.Cliente;
import jakarta.ws.rs.client.Client;

public class ClientResponseDTO {
    private Long id;
    private String nome;
    private String cpf;
    private String telefoneContato;
    private String email;

    public ClientResponseDTO(Cliente cliente) {
        this.id = cliente.getId();
        this.nome = cliente.getNome();
        this.cpf = cliente.getCpf();
        this.telefoneContato = cliente.getTelefoneContato();
        this.email = cliente.getEmail();
    }

    public String getCpf() {
        return cpf;
    }

    public String getTelefoneContato() {
        return telefoneContato;
    }


    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }
}
