package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class ResultResponsaveisDTO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private boolean resultado;
    private String mensagem;
    private String sessionID;

    public boolean isResultado() {
        return resultado;
    }

    public void setResultado(final boolean resultado) {
        this.resultado = resultado;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(final String mensagem) {
        this.mensagem = mensagem;
    }

    public void concatMensagem(final String mensagem) {
        if (this.mensagem == null || this.mensagem.trim().equals("")) {
            this.mensagem = mensagem;
        } else {
            this.mensagem += "\r\n" + mensagem;
        }
    }

    public String getSessionID() {
        return sessionID;
    }

    public void setSessionID(final String sessionID) {
        this.sessionID = sessionID;
    }

}
