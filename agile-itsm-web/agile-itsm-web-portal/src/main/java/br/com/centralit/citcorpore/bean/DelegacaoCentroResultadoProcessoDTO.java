package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class DelegacaoCentroResultadoProcessoDTO extends BaseEntity {
	private Integer idDelegacaoCentroResultado;
	private Integer idProcessoNegocio;

	public Integer getIdDelegacaoCentroResultado(){
		return this.idDelegacaoCentroResultado;
	}
	public void setIdDelegacaoCentroResultado(Integer parm){
		this.idDelegacaoCentroResultado = parm;
	}

	public Integer getIdProcessoNegocio(){
		return this.idProcessoNegocio;
	}
	public void setIdProcessoNegocio(Integer parm){
		this.idProcessoNegocio = parm;
	}

}
