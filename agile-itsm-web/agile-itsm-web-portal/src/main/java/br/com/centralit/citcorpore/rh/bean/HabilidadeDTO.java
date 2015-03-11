package br.com.centralit.citcorpore.rh.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class HabilidadeDTO extends BaseEntity {
	private Integer idHabilidade;
	private String descricao;
	private String detalhe;
	
	public String getDetalhe() {
		return detalhe;
	}
	public void setDetalhe(String detalhe) {
		this.detalhe = detalhe;
	}
	
	public Integer getIdHabilidade() {
		return idHabilidade;
	}
	public void setIdHabilidade(Integer idHabilidade) {
		this.idHabilidade = idHabilidade;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
}