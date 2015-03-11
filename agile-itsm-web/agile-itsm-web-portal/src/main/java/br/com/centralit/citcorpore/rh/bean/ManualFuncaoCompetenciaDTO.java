package br.com.centralit.citcorpore.rh.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class ManualFuncaoCompetenciaDTO extends BaseEntity{

	private static final long serialVersionUID = 1L;

	private Integer idNivelCompetencia;
	private Integer nivel;
	private String descricao;
	
	public Integer getIdNivelCompetencia() {
		return idNivelCompetencia;
	}
	public void setIdNivelCompetencia(Integer idNivelCompetencia) {
		this.idNivelCompetencia = idNivelCompetencia;
	}
	public Integer getNivel() {
		return nivel;
	}
	public void setNivel(Integer nivel) {
		this.nivel = nivel;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
