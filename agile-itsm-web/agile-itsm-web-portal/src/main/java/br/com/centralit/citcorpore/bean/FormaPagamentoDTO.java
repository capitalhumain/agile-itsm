package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

@SuppressWarnings("serial")
public class FormaPagamentoDTO extends BaseEntity{
	
	private Integer idFormaPagamento;
	private String nomeFormaPagamento;
	private String situacao;
	
	public Integer getIdFormaPagamento() {
		return idFormaPagamento;
	}
	public void setIdFormaPagamento(Integer idFormaPagamento) {
		this.idFormaPagamento = idFormaPagamento;
	}
	public String getNomeFormaPagamento() {
		return nomeFormaPagamento;
	}
	public void setNomeFormaPagamento(String nomeFormaPagamento) {
		this.nomeFormaPagamento = nomeFormaPagamento;
	}
	public String getSituacao() {
		return situacao;
	}
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
	
	
}
