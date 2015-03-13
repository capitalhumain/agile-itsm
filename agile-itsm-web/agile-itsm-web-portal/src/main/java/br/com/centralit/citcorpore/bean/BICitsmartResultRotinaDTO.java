package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class BICitsmartResultRotinaDTO extends BaseEntity {

    private static final long serialVersionUID = 1L;
    private boolean resultado;
    private final StringBuilder mensagem;
    private String sessionID;

    public BICitsmartResultRotinaDTO() {
        super();
        mensagem = new StringBuilder();
    }

    public boolean isResultado() {
        return resultado;
    }

    public void setResultado(final boolean resultado) {
        this.resultado = resultado;
    }

    public String getMensagem() {
        if (mensagem == null) {
            return null;
        } else {
            return mensagem.toString();
        }
    }

    public void setMensagem(final String mensagem) {
        this.mensagem.delete(0, this.mensagem.length());
        this.concatMensagem(mensagem);
    }

    public void concatMensagem(final String mensagem) {
        if (this.mensagem.length() > 0) {
            this.mensagem.append("\r\n");
        }
        this.mensagem.append(mensagem);
    }

    public String getSessionID() {
        return sessionID;
    }

    public void setSessionID(final String sessionID) {
        this.sessionID = sessionID;
    }
}
