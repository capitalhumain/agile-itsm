package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class ChatSmartDTO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String nomeUsuarioConversando;

    public String getNomeUsuarioConversando() {
        return nomeUsuarioConversando;
    }

    public void setNomeUsuarioConversando(final String nomeUsuarioConversando) {
        this.nomeUsuarioConversando = nomeUsuarioConversando;
    }

}
