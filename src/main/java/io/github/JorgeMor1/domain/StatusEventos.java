package io.github.JorgeMor1.domain;

public enum StatusEventos {
    AGUARDANDO("Aguardando"),
    ANDAMENTO("Andamento"),
    FINALIZADO_SUCESSO("Sucesso"),
    FINALIZADO_INSUCESSO("Insucesso");

    private String status;

    StatusEventos(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
