package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class LimiteAprovacaoAutoridadeDTO extends BaseEntity {
	private Integer idLimiteAprovacao;
	private Integer idNivelAutoridade;

	public Integer getIdLimiteAprovacao(){
		return this.idLimiteAprovacao;
	}
	public void setIdLimiteAprovacao(Integer parm){
		this.idLimiteAprovacao = parm;
	}

	public Integer getIdNivelAutoridade(){
		return this.idNivelAutoridade;
	}
	public void setIdNivelAutoridade(Integer parm){
		this.idNivelAutoridade = parm;
	}

}
