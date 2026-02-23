package io.github.JorgeMor1.dto;

import io.github.JorgeMor1.domain.StatusEventos;

public class EventosDTO {
    private Long clienteId;
    private String origem;

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }
}
