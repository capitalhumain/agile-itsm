package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class BIDashBoardSegurDTO extends BaseEntity {
	private Integer idGrupo;
	private Integer idDashBoard;
	
	private Integer[] perfilSelecionado;

	public Integer getIdDashBoard(){
		return this.idDashBoard;
	}
	public void setIdDashBoard(Integer parm){
		this.idDashBoard = parm;
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
