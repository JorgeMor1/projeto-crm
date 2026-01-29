package io.github.JorgeMor1.domain;

public enum StatusLeads {
    AGUARDANDO("Aguardando"),
    ANDAMENTO("Andamento"),
    FINALIZADO_SUCESSO("Sucesso"),
    FINALIZADO_INSUCESSO("Insucesso");

    private String status;

    StatusLeads(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
