package io.github.JorgeMor1.dto;

import io.github.JorgeMor1.domain.Cliente;
import jakarta.ws.rs.client.Client;

public class ClientResponseDTO {

    private Long id;
    private String nome;
    private String email;

    public ClientResponseDTO(Cliente client) {
        this.id = client.getId();
        this.nome = client.getNome();
        this.email = client.getEmail();
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
