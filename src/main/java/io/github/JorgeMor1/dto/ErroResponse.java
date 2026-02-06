package io.github.JorgeMor1.dto;

import java.time.LocalDateTime;

public class ErroResponse {
    public int status;
    public String erro;
    public String mensagem;
    public LocalDateTime timestamp;

    public ErroResponse(int status, String erro, String mensagem) {
        this.status = status;
        this.erro = erro;
        this.mensagem = mensagem;
        this.timestamp = LocalDateTime.now();
    }
}
