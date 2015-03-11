package br.com.centralit.citcorpore.rh.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class PerspectivaTecnicaDTO extends BaseEntity {
	private Integer idPerspectivaTecnica;
	private String descricaoPerspectivaTecnica;
	private Integer nivelPerspectivaTecnica;
	
	public Integer getIdPerspectivaTecnica() {
		return idPerspectivaTecnica;
	}
	public void setIdPerspectivaTecnica(Integer idPerspectivaTecnica) {
		this.idPerspectivaTecnica = idPerspectivaTecnica;
	}
	public String getDescricaoPerspectivaTecnica() {
		return descricaoPerspectivaTecnica;
	}
	public void setDescricaoPerspectivaTecnica(String descricaoPerspectivaTecnica) {
		this.descricaoPerspectivaTecnica = descricaoPerspectivaTecnica;
	}
	public Integer getNivelPerspectivaTecnica() {
		return nivelPerspectivaTecnica;
	}
	public void setNivelPerspectivaTecnica(Integer nivelPerspectivaTecnica) {
		this.nivelPerspectivaTecnica = nivelPerspectivaTecnica;
	}
}