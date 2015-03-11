package br.com.centralit.citcorpore.rh.bean;

import java.sql.Date;

import br.com.agileitsm.model.support.BaseEntity;

/**
 * @author david.silva
 *
 */
public class VisualizarHistoricoFuncionalDTO extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	private String titulo;
	private String descricao;
	private Integer idCandidato;
	private Integer idResponsavel;
	private Date dtcriacao;
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Integer getIdCandidato() {
		return idCandidato;
	}
	public void setIdCandidato(Integer idCandidato) {
		this.idCandidato = idCandidato;
	}
	public Integer getIdResponsavel() {
		return idResponsavel;
	}
	public void setIdResponsavel(Integer idResponsavel) {
		this.idResponsavel = idResponsavel;
	}
	public Date getDtcriacao() {
		return dtcriacao;
	}
	public void setDtcriacao(Date dtcriacao) {
		this.dtcriacao = dtcriacao;
	}
	
}
