package br.com.centralit.citcorpore.rh.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class CompetenciasTecnicasDTO extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer idCompetenciasTecnicas;
	private String descricaoCompetenciasTecnicas;
	private Integer nivelCompetenciasTecnicas;
	private Integer idSolicitacaoServico;
	
	public Integer getIdCompetenciasTecnicas() {
		return idCompetenciasTecnicas;
	}
	public void setIdCompetenciasTecnicas(Integer idCompetenciasTecnicas) {
		this.idCompetenciasTecnicas = idCompetenciasTecnicas;
	}
	public String getDescricaoCompetenciasTecnicas() {
		return descricaoCompetenciasTecnicas;
	}
	public void setDescricaoCompetenciasTecnicas(
			String descricaoCompetenciasTecnicas) {
		this.descricaoCompetenciasTecnicas = descricaoCompetenciasTecnicas;
	}
	public Integer getNivelCompetenciasTecnicas() {
		return nivelCompetenciasTecnicas;
	}
	public void setNivelCompetenciasTecnicas(Integer nivelCompetenciasTecnicas) {
		this.nivelCompetenciasTecnicas = nivelCompetenciasTecnicas;
	}
	public Integer getIdSolicitacaoServico() {
		return idSolicitacaoServico;
	}
	public void setIdSolicitacaoServico(Integer idSolicitacaoServico) {
		this.idSolicitacaoServico = idSolicitacaoServico;
	}
	
	
	
	
}