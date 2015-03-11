package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class RelEscalonamentoMudancaDto extends BaseEntity{

	/**
	 * Relaciona as solicita��es de servi�o ao escalonamento
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer idRequisicaoMudanca;
	private Integer idEscalonamento;
	
	public Integer getIdRequisicaoMudanca() {
		return idRequisicaoMudanca;
	}
	public void setIdRequisicaoMudanca(Integer idRequisicaoMudanca) {
		this.idRequisicaoMudanca = idRequisicaoMudanca;
	}
	public Integer getIdEscalonamento() {
		return idEscalonamento;
	}
	public void setIdEscalonamento(Integer idEscalonamento) {
		this.idEscalonamento = idEscalonamento;
	}
	
}
