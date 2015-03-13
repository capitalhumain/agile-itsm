package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

/**
 * @author euler.ramos Guardará o resultado da análise de tendências de lançamento de solicitações de serviço
 */
public class TendenciaDTO extends BaseEntity {

	private static final long serialVersionUID = -42994999516790078L;

	private Integer id;
	private String descricao;
	private Integer qtdeCritica;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getQtdeCritica() {
		return qtdeCritica;
	}

	public void setQtdeCritica(Integer qtdeCritica) {
		this.qtdeCritica = qtdeCritica;
	}

}
