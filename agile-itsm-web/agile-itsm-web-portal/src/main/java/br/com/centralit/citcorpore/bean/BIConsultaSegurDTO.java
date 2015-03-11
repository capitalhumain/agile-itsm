package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class BIConsultaSegurDTO extends BaseEntity {
	private Integer idGrupo;
	private Integer idConsulta;
	
	private Integer[] perfilSelecionado;

	public Integer getIdConsulta(){
		return this.idConsulta;
	}
	public void setIdConsulta(Integer parm){
		this.idConsulta = parm;
	}
	public Integer[] getPerfilSelecionado() {
		return perfilSelecionado;
	}
	public void setPerfilSelecionado(Integer[] perfilSelecionado) {
		this.perfilSelecionado = perfilSelecionado;
	}
	public Integer getIdGrupo() {
		return idGrupo;
	}
	public void setIdGrupo(Integer idGrupo) {
		this.idGrupo = idGrupo;
	}

}
