package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class RelacionamentoProdutoDTO extends BaseEntity {

    private Integer idTipoProduto;
    private Integer idTipoProdutoRelacionado;
    private String tipoRelacionamento;

    public Integer getIdTipoProduto() {
        return idTipoProduto;
    }

    public void setIdTipoProduto(final Integer idTipoProduto) {
        this.idTipoProduto = idTipoProduto;
    }

    public Integer getIdTipoProdutoRelacionado() {
        return idTipoProdutoRelacionado;
    }

    public void setIdTipoProdutoRelacionado(final Integer idTipoProdutoRelacionado) {
        this.idTipoProdutoRelacionado = idTipoProdutoRelacionado;
    }

    public String getTipoRelacionamento() {
        return tipoRelacionamento;
    }

    public void setTipoRelacionamento(final String tipoRelacionamento) {
        this.tipoRelacionamento = tipoRelacionamento;
    }

}
