package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class ChatSmartDTO extends BaseEntity {
	// chat
	private static final long serialVersionUID = 1L;
	// tabela chat
	private String nomeUsuarioConversando;

	public String getNomeUsuarioConversando() {
		return nomeUsuarioConversando;
	}

	public void setNomeUsuarioConversando(String nomeUsuarioConversando) {
		this.nomeUsuarioConversando = nomeUsuarioConversando;
	}

}