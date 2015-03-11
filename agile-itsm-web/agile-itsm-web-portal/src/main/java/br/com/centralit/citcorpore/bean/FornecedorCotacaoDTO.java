package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class FornecedorCotacaoDTO extends BaseEntity {
	private Integer idCotacao;
	private Integer idFornecedor;

	public Integer getIdCotacao(){
		return this.idCotacao;
	}
	public void setIdCotacao(Integer parm){
		this.idCotacao = parm;
	}

	public Integer getIdFornecedor(){
		return this.idFornecedor;
	}
	public void setIdFornecedor(Integer parm){
		this.idFornecedor = parm;
	}

}
