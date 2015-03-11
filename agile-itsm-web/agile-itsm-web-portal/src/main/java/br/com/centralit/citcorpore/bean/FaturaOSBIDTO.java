package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

@SuppressWarnings("serial")
public class FaturaOSBIDTO extends BaseEntity {
	private Integer idFatura;
	private Integer idOs;
	private Integer idConexaoBI;

	public Integer getIdFatura(){
		return this.idFatura;
	}
	public void setIdFatura(Integer parm){
		this.idFatura = parm;
	}

	public Integer getIdOs(){
		return this.idOs;
	}
	public void setIdOs(Integer parm){
		this.idOs = parm;
	}
	public Integer getIdConexaoBI() {
		return idConexaoBI;
	}
	public void setIdConexaoBI(Integer idConexaoBI) {
		this.idConexaoBI = idConexaoBI;
	}
	
}
