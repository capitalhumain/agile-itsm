package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.util.Enumerados.SituacaoCotacaoItemRequisicao;
import br.com.citframework.util.UtilFormatacao;
import br.com.citframework.util.UtilStrings;

public class CotacaoItemRequisicaoDTO extends BaseEntity {

    private Integer idColetaPreco;
    private Integer idItemRequisicaoProduto;
    private Integer idParecer;
    private Integer idItemTrabalho;
    private Integer idSolicitacaoServico;
    private Integer idCotacao;
    private Double quantidade;
    private Double quantidadeEntregue;
    private String situacao;

    private Double percVariacaoPreco;

    private Integer idProduto;
    private Integer idMarca;
    private Integer idUnidadeMedida;
    private String descricaoItem;

    private Integer idCategoriaProduto;
    private String codigoProduto;
    private String nomeProduto;
    private String nomeCategoria;
    private Integer idJustificativa;
    private String complementoJustificativa;

    private String siglaUnidadeMedida;
    private String descrSituacao;

    private String cpfCnpjFornecedor;
    private String nomeFornecedor;
    private String tipoFornecedor;

    private String aprovado;

    private String especificacoes;
    private Double preco;
    private Double valorDesconto;
    private Double valorAcrescimo;
    private Double valorFrete;
    private Integer prazoEntrega;
    private Double taxaJuros;
    private Double valorTotal;

    private Integer idItemCotacao;

    private String descricaoFmtHtml;

    private Integer idParecerAutorizacao;
    private String finalidade;

    public Integer getIdColetaPreco() {
        return idColetaPreco;
    }

    public void setIdColetaPreco(final Integer parm) {
        idColetaPreco = parm;
    }

    public Integer getIdItemRequisicaoProduto() {
        return idItemRequisicaoProduto;
    }

    public void setIdItemRequisicaoProduto(final Integer parm) {
        idItemRequisicaoProduto = parm;
    }

    public Integer getIdParecer() {
        return idParecer;
    }

    public void setIdParecer(final Integer parm) {
        idParecer = parm;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(final Double parm) {
        quantidade = parm;
    }

    public String getSituacao() {
        return situacao;
    }

    public Integer getIdSolicitacaoServico() {
        return idSolicitacaoServico;
    }

    public void setIdSolicitacaoServico(final Integer idSolicitacaoServico) {
        this.idSolicitacaoServico = idSolicitacaoServico;
    }

    public Integer getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(final Integer idProduto) {
        this.idProduto = idProduto;
    }

    public Integer getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(final Integer idMarca) {
        this.idMarca = idMarca;
    }

    public Integer getIdUnidadeMedida() {
        return idUnidadeMedida;
    }

    public void setIdUnidadeMedida(final Integer idUnidadeMedida) {
        this.idUnidadeMedida = idUnidadeMedida;
    }

    public String getDescricaoItem() {
        return descricaoItem;
    }

    public void setDescricaoItem(final String descricaoItem) {
        this.descricaoItem = descricaoItem;
    }

    public Integer getIdCategoriaProduto() {
        return idCategoriaProduto;
    }

    public void setIdCategoriaProduto(final Integer idCategoriaProduto) {
        this.idCategoriaProduto = idCategoriaProduto;
    }

    public String getCodigoProduto() {
        return codigoProduto;
    }

    public void setCodigoProduto(final String codigoProduto) {
        this.codigoProduto = codigoProduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(final String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public String getComplementoJustificativa() {
        return complementoJustificativa;
    }

    public void setComplementoJustificativa(final String complementoJustificativa) {
        this.complementoJustificativa = complementoJustificativa;
    }

    public String getSiglaUnidadeMedida() {
        return siglaUnidadeMedida;
    }

    public void setSiglaUnidadeMedida(final String siglaUnidadeMedida) {
        this.siglaUnidadeMedida = siglaUnidadeMedida;
    }

    public String getDescrSituacao() {
        return descrSituacao;
    }

    public void setDescrSituacao(final String descrSituacao) {
        this.descrSituacao = descrSituacao;
    }

    public String getCpfCnpjFornecedor() {
        return cpfCnpjFornecedor;
    }

    public void setCpfCnpjFornecedor(final String cpfCnpjFornecedor) {
        this.cpfCnpjFornecedor = cpfCnpjFornecedor;
    }

    public String getNomeFornecedor() {
        return nomeFornecedor;
    }

    public void setNomeFornecedor(final String nomeFornecedor) {
        this.nomeFornecedor = nomeFornecedor;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public void setNomeCategoria(final String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }

    public void setSituacao(final String parm) {
        descrSituacao = "";
        situacao = parm;
        try {
            if (situacao != null) {
                descrSituacao = SituacaoCotacaoItemRequisicao.valueOf(situacao.trim()).getDescricao();
            }
        } catch (final Exception e) {}
    }

    public String getAprovado() {
        return aprovado;
    }

    public void setAprovado(final String aprovado) {
        this.aprovado = aprovado;
    }

    public String getEspecificacoes() {
        return especificacoes;
    }

    public void setEspecificacoes(final String especificacoes) {
        this.especificacoes = especificacoes;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(final Double preco) {
        this.preco = preco;
    }

    public Double getValorDesconto() {
        return valorDesconto;
    }

    public void setValorDesconto(final Double valorDesconto) {
        this.valorDesconto = valorDesconto;
    }

    public Double getValorAcrescimo() {
        return valorAcrescimo;
    }

    public void setValorAcrescimo(final Double valorAcrescimo) {
        this.valorAcrescimo = valorAcrescimo;
    }

    public Double getValorFrete() {
        return valorFrete;
    }

    public void setValorFrete(final Double valorFrete) {
        this.valorFrete = valorFrete;
    }

    public Integer getPrazoEntrega() {
        return prazoEntrega;
    }

    public void setPrazoEntrega(final Integer prazoEntrega) {
        this.prazoEntrega = prazoEntrega;
    }

    public Double getTaxaJuros() {
        return taxaJuros;
    }

    public void setTaxaJuros(final Double taxaJuros) {
        this.taxaJuros = taxaJuros;
    }

    public Integer getIdJustificativa() {
        return idJustificativa;
    }

    public void setIdJustificativa(final Integer idJustificativa) {
        this.idJustificativa = idJustificativa;
    }

    public Integer getIdCotacao() {
        return idCotacao;
    }

    public void setIdCotacao(final Integer idCotacao) {
        this.idCotacao = idCotacao;
    }

    public Integer getIdItemTrabalho() {
        return idItemTrabalho;
    }

    public void setIdItemTrabalho(final Integer idItemTrabalho) {
        this.idItemTrabalho = idItemTrabalho;
    }

    public Double getQuantidadeEntregue() {
        return quantidadeEntregue;
    }

    public void setQuantidadeEntregue(final Double quantidadeEntregue) {
        this.quantidadeEntregue = quantidadeEntregue;
    }

    public Double getPercVariacaoPreco() {
        return percVariacaoPreco;
    }

    public void setPercVariacaoPreco(final Double percVariacaoPreco) {
        this.percVariacaoPreco = percVariacaoPreco;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(final Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getDescricaoFmtHtml() {
        descricaoFmtHtml = "";
        if (UtilStrings.isNotVazio(especificacoes)) {
            descricaoFmtHtml += "<p><b>Especificações:</b> " + especificacoes + "</p>";
        }
        if (UtilStrings.isNotVazio(nomeFornecedor)) {
            descricaoFmtHtml += "<p><b>CPF/CNPJ Fornecedor:</b> ";
            if (UtilStrings.nullToVazio(tipoFornecedor).equalsIgnoreCase("J")) {
                descricaoFmtHtml += UtilFormatacao.formataCnpj(cpfCnpjFornecedor);
            } else {
                descricaoFmtHtml += UtilFormatacao.formataCpf(cpfCnpjFornecedor);
            }
            descricaoFmtHtml += "<br><b>Nome Fornecedor:</b> " + nomeFornecedor + "</p>";
        }
        // descricaoFmtHtml += "<p><b>Situação:</b> "+this.descrSituacao+"</p>";
        return descricaoFmtHtml;
    }

    public void setDescricaoFmtHtml(final String descricaoFmtHtml) {
        this.descricaoFmtHtml = descricaoFmtHtml;
    }

    public String getTipoFornecedor() {
        return tipoFornecedor;
    }

    public void setTipoFornecedor(final String tipoFornecedor) {
        this.tipoFornecedor = tipoFornecedor;
    }

    public Integer getIdItemCotacao() {
        return idItemCotacao;
    }

    public void setIdItemCotacao(final Integer idItemCotacao) {
        this.idItemCotacao = idItemCotacao;
    }

    public Integer getIdParecerAutorizacao() {
        return idParecerAutorizacao;
    }

    public void setIdParecerAutorizacao(final Integer idParecerAutorizacao) {
        this.idParecerAutorizacao = idParecerAutorizacao;
    }

    public String getFinalidade() {
        return finalidade;
    }

    public void setFinalidade(final String finalidade) {
        this.finalidade = finalidade;
    }

}
