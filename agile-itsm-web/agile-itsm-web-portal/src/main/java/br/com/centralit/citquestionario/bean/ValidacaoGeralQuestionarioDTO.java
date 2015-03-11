package br.com.centralit.citquestionario.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class ValidacaoGeralQuestionarioDTO extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -581444197536445647L;
	private String validacao;

	public String getValidacao() {
		if (this.validacao == null){
			this.validacao = "";
		}
		return validacao;
	}

	public void setValidacao(String validacao) {
		this.validacao = validacao;
	}
	
	public void addValidacao(String validacaoParm) {
		if (this.validacao == null){
			this.validacao = "";
		}
		this.validacao += validacaoParm;
	}
}
