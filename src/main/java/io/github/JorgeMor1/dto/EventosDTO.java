package io.github.JorgeMor1.dto;

import io.github.JorgeMor1.domain.StatusEventos;

public class EventosDTO {
    private Long clienteId;
    private String origem;
    private String usuarioLogin;


    public String getUsuarioLogin() {
        return usuarioLogin;
    }

    public void setUsuarioLogin(String usuarioLogin) {
        this.usuarioLogin = usuarioLogin;
    }

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
