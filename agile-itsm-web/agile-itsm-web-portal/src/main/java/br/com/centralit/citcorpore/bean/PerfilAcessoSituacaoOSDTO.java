package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class PerfilAcessoSituacaoOSDTO extends BaseEntity {
	private Integer idPerfil;
	private Integer situacaoOs;
	private java.sql.Date dataInicio;
	private java.sql.Date dataFim;

	public Integer getIdPerfil(){
		return this.idPerfil;
	}
	public void setIdPerfil(Integer parm){
		this.idPerfil = parm;
	}

	public Integer getSituacaoOs(){
		return this.situacaoOs;
	}
	public void setSituacaoOs(Integer parm){
		this.situacaoOs = parm;
	}

	public java.sql.Date getDataInicio(){
		return this.dataInicio;
	}
	public void setDataInicio(java.sql.Date parm){
		this.dataInicio = parm;
	}

	public java.sql.Date getDataFim(){
		return this.dataFim;
	}
	public void setDataFim(java.sql.Date parm){
		this.dataFim = parm;
	}

}
