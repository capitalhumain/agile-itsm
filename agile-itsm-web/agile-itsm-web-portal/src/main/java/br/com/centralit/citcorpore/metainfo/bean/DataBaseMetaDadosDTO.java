package br.com.centralit.citcorpore.metainfo.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class DataBaseMetaDadosDTO extends BaseEntity {
	private String nomeTabela;

	public String getNomeTabela() {
		return nomeTabela;
	}

	public void setNomeTabela(String nomeTabela) {
		this.nomeTabela = nomeTabela;
	}
}
