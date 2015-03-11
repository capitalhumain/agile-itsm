package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class SituacaoDemandaDTO extends BaseEntity {
	public static final Integer SITUACAO_NAO_INICIADA = new Integer(1);
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7260448806616843990L;
	private Integer idSituacaoDemanda;
	private String nomeSituacao;
	public Integer getIdSituacaoDemanda() {
		return idSituacaoDemanda;
	}
	public void setIdSituacaoDemanda(Integer idSituacaoDemanda) {
		this.idSituacaoDemanda = idSituacaoDemanda;
	}
	public String getNomeSituacao() {
		return nomeSituacao;
	}
	public void setNomeSituacao(String nomeSituacao) {
		this.nomeSituacao = nomeSituacao;
	}
}
