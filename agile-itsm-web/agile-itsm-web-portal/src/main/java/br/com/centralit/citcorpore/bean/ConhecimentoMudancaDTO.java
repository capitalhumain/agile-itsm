package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class ConhecimentoMudancaDTO extends BaseEntity {

	/**
     * 
     */
	private static final long serialVersionUID = 1L;
	private Integer idRequisicaoMudanca;
	private Integer idBaseConhecimento;
	/**
	 * @return the idRequisicaoMudanca
	 */
	public Integer getIdRequisicaoMudanca() {
		return idRequisicaoMudanca;
	}
	/**
	 * @param idRequisicaoMudanca the idRequisicaoMudanca to set
	 */
	public void setIdRequisicaoMudanca(Integer idRequisicaoMudanca) {
		this.idRequisicaoMudanca = idRequisicaoMudanca;
	}
	/**
	 * @return the idBaseConhecimento
	 */
	public Integer getIdBaseConhecimento() {
		return idBaseConhecimento;
	}
	/**
	 * @param idBaseConhecimento the idBaseConhecimento to set
	 */
	public void setIdBaseConhecimento(Integer idBaseConhecimento) {
		this.idBaseConhecimento = idBaseConhecimento;
	}



}
