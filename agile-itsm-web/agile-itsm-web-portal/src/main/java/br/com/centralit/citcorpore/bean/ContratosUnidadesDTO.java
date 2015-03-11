package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class ContratosUnidadesDTO extends BaseEntity {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7907426960403057527L;
	
	private Integer idUnidade;
	private Integer idContrato;
	
	
	public Integer getIdUnidade() {
		return idUnidade;
	}
	public void setIdUnidade(Integer idUnidade) {
		this.idUnidade = idUnidade;
	}
	public Integer getIdContrato() {
		return idContrato;
	}
	public void setIdContrato(Integer idContrato) {
		this.idContrato = idContrato;
	}

}
