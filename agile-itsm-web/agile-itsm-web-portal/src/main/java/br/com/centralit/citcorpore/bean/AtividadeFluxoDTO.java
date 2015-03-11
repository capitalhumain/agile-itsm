package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class AtividadeFluxoDTO extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2541601608017415771L;
	private Integer idAtividade;
	private Integer idFluxo;
	public Integer getIdAtividade() {
		return idAtividade;
	}
	public void setIdAtividade(Integer idAtividade) {
		this.idAtividade = idAtividade;
	}
	public Integer getIdFluxo() {
		return idFluxo;
	}
	public void setIdFluxo(Integer idFluxo) {
		this.idFluxo = idFluxo;
	}
}
