package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class TipoEventoServicoDTO extends BaseEntity {
	private Integer idTipoEventoServico;
	private String nomeTipoEventoServico;

	public Integer getIdTipoEventoServico(){
		return this.idTipoEventoServico;
	}
	public void setIdTipoEventoServico(Integer parm){
		this.idTipoEventoServico = parm;
	}

	public String getNomeTipoEventoServico(){
		return this.nomeTipoEventoServico;
	}
	public void setNomeTipoEventoServico(String parm){
		this.nomeTipoEventoServico = parm;
	}

}
