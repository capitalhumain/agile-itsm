package br.com.centralit.citcorpore.rh.bean;

import br.com.agileitsm.model.support.BaseEntity;


public class JornadaEmpregadoDTO extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private Integer idJornada;
	private String descricao;
	private String escala;
	private String considerarFeriados;
	
	public Integer getIdJornada() {
		return idJornada;
	}
	public void setIdJornada(Integer idJornada) {
		this.idJornada = idJornada;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getEscala() {
		return escala;
	}
	public void setEscala(String escala) {
		this.escala = escala;
	}
	public String getConsiderarFeriados() {
		return considerarFeriados;
	}
	public void setConsiderarFeriados(String considerarFeriados) {
		this.considerarFeriados = considerarFeriados;
	}
	public String getIdentificacao() {
		return this.idJornada+" - "+this.descricao;
	}
	
	
}