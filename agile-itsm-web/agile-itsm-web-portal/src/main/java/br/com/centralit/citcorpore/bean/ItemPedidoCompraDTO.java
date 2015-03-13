package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class ItemPedidoCompraDTO extends BaseEntity {

    private Integer idItemPedido;
    private Integer idPedido;
    private Integer idColetaPreco;
    private Integer idProduto;
    private Double quantidade;
    private Double valorTotal;
    private Double valorDesconto;
    private Double valorAcrescimo;
    private Double baseCalculoIcms;
    private Double aliquotaIcms;
    private Double aliquotaIpi;
    private Double valorFrete;

    private String descricaoItem;
    private String codigoProduto;

    private Integer idSolicitacaoServico;
    private Integer idParecerValidacao;
    private Integer idParecerAutorizacao;
    private Integer idParecerCotacao;

    private String autoridadeValidacao;
    private String autoridadeAprovacao;
    private String autoridadeCotacao;

    private Double valorLiquido;

    public Integer getIdItemPedido() {
        return idItemPedido;
    }

    public void setIdItemPedido(final Integer parm) {
        idItemPedido = parm;
    }

    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(final Integer parm) {
        idPedido = parm;
    }

    public Integer getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(final Integer parm) {
        idProduto = parm;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(final Double parm) {
        quantidade = parm;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(final Double parm) {
        valorTotal = parm;
    }

    public Double getValorDesconto() {
        return valorDesconto;
    }

    public void setValorDesconto(final Double parm) {
        valorDesconto = parm;
    }

    public Double getValorAcrescimo() {
        return valorAcrescimo;
    }

    public void setValorAcrescimo(final Double parm) {
        valorAcrescimo = parm;
    }

    public Double getBaseCalculoIcms() {
        return baseCalculoIcms;
    }

    public void setBaseCalculoIcms(final Double parm) {
        baseCalculoIcms = parm;
    }

    public Double getAliquotaIcms() {
        return aliquotaIcms;
    }

    public void setAliquotaIcms(final Double parm) {
        aliquotaIcms = parm;
    }

    public Double getAliquotaIpi() {
        return aliquotaIpi;
    }

    public void setAliquotaIpi(final Double parm) {
        aliquotaIpi = parm;
    }

    public Integer getIdColetaPreco() {
        return idColetaPreco;
    }

    public void setIdColetaPreco(final Integer idColetaPreco) {
        this.idColetaPreco = idColetaPreco;
    }

    public String getDescricaoItem() {
        return descricaoItem;
    }

    public void setDescricaoItem(final String descricaoItem) {
        this.descricaoItem = descricaoItem;
    }

    public String getCodigoProduto() {
        return codigoProduto;
    }

    public void setCodigoProduto(final String codigoProduto) {
        this.codigoProduto = codigoProduto;
    }

    public Double getValorFrete() {
        return valorFrete;
    }

    public void setValorFrete(final Double valorFrete) {
        this.valorFrete = valorFrete;
    }

    public Integer getIdSolicitacaoServico() {
        return idSolicitacaoServico;
    }

    public void setIdSolicitacaoServico(final Integer idSolicitacaoServico) {
        this.idSolicitacaoServico = idSolicitacaoServico;
    }

    public Integer getIdParecerValidacao() {
        return idParecerValidacao;
    }

    public void setIdParecerValidacao(final Integer idParecerValidacao) {
        this.idParecerValidacao = idParecerValidacao;
    }

    public Integer getIdParecerAutorizacao() {
        return idParecerAutorizacao;
    }

    public void setIdParecerAutorizacao(final Integer idParecerAutorizacao) {
        this.idParecerAutorizacao = idParecerAutorizacao;
    }

    public Integer getIdParecerCotacao() {
        return idParecerCotacao;
    }

    public void setIdParecerCotacao(final Integer idParecerCotacao) {
        this.idParecerCotacao = idParecerCotacao;
    }

    public String getAutoridadeValidacao() {
        return autoridadeValidacao;
    }

    public void setAutoridadeValidacao(final String autoridadeValidacao) {
        this.autoridadeValidacao = autoridadeValidacao;
    }

    public String getAutoridadeAprovacao() {
        return autoridadeAprovacao;
    }

    public void setAutoridadeAprovacao(final String autoridadeAprovacao) {
        this.autoridadeAprovacao = autoridadeAprovacao;
    }

    public String getAutoridadeCotacao() {
        return autoridadeCotacao;
    }

    public void setAutoridadeCotacao(final String autoridadeCotacao) {
        this.autoridadeCotacao = autoridadeCotacao;
    }

    public Double getValorLiquido() {
        valorLiquido = valorTotal;
        if (valorDesconto != null) {
            valorLiquido = valorLiquido - valorDesconto;
        }
        if (valorAcrescimo != null) {
            valorLiquido = valorLiquido + valorAcrescimo;
        }
        return valorLiquido;
    }

    public void setValorLiquido(final Double valorLiquido) {
        this.valorLiquido = valorLiquido;
    }

}
