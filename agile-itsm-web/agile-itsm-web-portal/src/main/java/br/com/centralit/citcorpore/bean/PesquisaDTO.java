package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class PesquisaDTO extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	private String pesquisa;

	public String getPesquisa() {
		return pesquisa;
	}

	public void setPesquisa(String pesquisa) {
		this.pesquisa = pesquisa;
	}
    
   
}
