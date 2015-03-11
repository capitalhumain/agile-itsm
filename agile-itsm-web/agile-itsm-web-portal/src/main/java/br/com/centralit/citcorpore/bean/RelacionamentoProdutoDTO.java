package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class RelacionamentoProdutoDTO extends BaseEntity {
	private Integer idTipoProduto;
	private Integer idTipoProdutoRelacionado;
	private String tipoRelacionamento;
	
    public Integer getIdTipoProduto() {
        return idTipoProduto;
    }
    public void setIdTipoProduto(Integer idTipoProduto) {
        this.idTipoProduto = idTipoProduto;
    }
    public Integer getIdTipoProdutoRelacionado() {
        return idTipoProdutoRelacionado;
    }
    public void setIdTipoProdutoRelacionado(Integer idTipoProdutoRelacionado) {
        this.idTipoProdutoRelacionado = idTipoProdutoRelacionado;
    }
	public String getTipoRelacionamento() {
		return tipoRelacionamento;
	}
	public void setTipoRelacionamento(String tipoRelacionamento) {
		this.tipoRelacionamento = tipoRelacionamento;
	}


}
