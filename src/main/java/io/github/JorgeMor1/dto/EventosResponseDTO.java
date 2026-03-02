package io.github.JorgeMor1.dto;

import io.github.JorgeMor1.domain.Eventos;
import jakarta.persistence.Column;
import jakarta.persistence.PrePersist;

import java.time.LocalDateTime;

public class EventosResponseDTO {
    private Long idEvento;
    private Long clienteId;
    private Long usuarioId;
    private String origem;
    private String statusEvento;
    private LocalDateTime createdAt;



    public EventosResponseDTO(Long idEvento, Long clienteId, Long usuarioId, String origem, String statusEvento, LocalDateTime createdAt) {
        this.idEvento = idEvento;
        this.clienteId = clienteId;
        this.usuarioId = usuarioId;
        this.statusEvento = statusEvento;
        this.origem = origem;
        this.createdAt = createdAt;
    }

    public EventosResponseDTO(Eventos eventos) {
        this.idEvento = eventos.getId();
        this.clienteId = eventos.getCliente().getId();
        this.usuarioId = eventos.getUsuario().getId();
        this.statusEvento = eventos.getStatusEvento().getStatus();
        this.origem = eventos.getOrigem();
        this.createdAt = eventos.getCreatedAt();
    }

    public Long getIdEvento() {
        return idEvento;
    }
    public String getStatusEvento() {
        return statusEvento;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }
}
