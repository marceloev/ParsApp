package com.pars.system.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ServerResponse {

    private ResponseType responseType = ResponseType.Nenhum;
    private String mensagem;

    public ServerResponse() {
    }

    public ServerResponse(String mensagem) {
        this.mensagem = mensagem;
    }

    public ServerResponse(ResponseType responseType, String mensagem) {
        this.responseType = responseType;
        this.mensagem = mensagem;
    }
}
