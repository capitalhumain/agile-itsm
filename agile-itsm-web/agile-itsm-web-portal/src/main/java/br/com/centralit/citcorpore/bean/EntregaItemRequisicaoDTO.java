package br.com.centralit.citcorpore.bean;

import java.sql.Date;
import java.util.Collection;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.util.Enumerados.SituacaoEntregaItemRequisicao;
import br.com.citframework.util.UtilDatas;
import br.com.citframework.util.UtilFormatacao;
import br.com.citframework.util.UtilStrings;

public class EntregaItemRequisicaoDTO extends BaseEntity {

    private Integer idEntrega;
    private Integer idPedido;
    private Integer idColetaPreco;
    private Integer idItemRequisicaoProduto;
    private Integer idSolicitacaoServico;
    private Integer idItemTrabalho;
    private Integer idParecer;
    private Double quantidadeEntregue;
    private String situacao;

    private Integer idJustificativa;
    private String complementoJustificativa;
    private String aprovado;
    private String descrSituacao;

    private String descricaoItem;
    private String numeroPedido;
    private Date dataEntrega;
    private String cpfCnpjFornecedor;
    private String nomeFornecedor;
    private String tipoFornecedor;

    private String descricaoFmtHtml;

    private UsuarioDTO usuarioDto;
    Collection<InspecaoEntregaItemDTO> colInspecao;

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(final String situacao) {
        this.situacao = situacao;
    }

    public Integer getIdEntrega() {
        return idEntrega;
    }

    public void setIdEntrega(final Integer idEntrega) {
        this.idEntrega = idEntrega;
    }

    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(final Integer idPedido) {
        this.idPedido = idPedido;
    }

    public Integer getIdColetaPreco() {
        return idColetaPreco;
    }

    public void setIdColetaPreco(final Integer idColetaPreco) {
        this.idColetaPreco = idColetaPreco;
    }

    public Integer getIdItemRequisicaoProduto() {
        return idItemRequisicaoProduto;
    }

    public void setIdItemRequisicaoProduto(final Integer idItemRequisicaoProduto) {
        this.idItemRequisicaoProduto = idItemRequisicaoProduto;
    }

    public Integer getIdSolicitacaoServico() {
        return idSolicitacaoServico;
    }

    public void setIdSolicitacaoServico(final Integer idSolicitacaoServico) {
        this.idSolicitacaoServico = idSolicitacaoServico;
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

    public String getNumeroPedido() {
        return numeroPedido;
    }

    public void setNumeroPedido(final String numeroPedido) {
        this.numeroPedido = numeroPedido;
    }

    public Date getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(final Date dataEntrega) {
        this.dataEntrega = dataEntrega;
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

    public String getDescricaoItem() {
        return descricaoItem;
    }

    public void setDescricaoItem(final String descricaoItem) {
        this.descricaoItem = descricaoItem;
    }

    public Collection<InspecaoEntregaItemDTO> getColInspecao() {
        return colInspecao;
    }

    public void setColInspecao(final Collection<InspecaoEntregaItemDTO> colInspecao) {
        this.colInspecao = colInspecao;
    }

    public UsuarioDTO getUsuarioDto() {
        return usuarioDto;
    }

    public void setUsuarioDto(final UsuarioDTO usuarioDto) {
        this.usuarioDto = usuarioDto;
    }

    public Integer getIdParecer() {
        return idParecer;
    }

    public void setIdParecer(final Integer idParecer) {
        this.idParecer = idParecer;
    }

    public Integer getIdJustificativa() {
        return idJustificativa;
    }

    public void setIdJustificativa(final Integer idJustificativa) {
        this.idJustificativa = idJustificativa;
    }

    public String getComplementoJustificativa() {
        return complementoJustificativa;
    }

    public void setComplementoJustificativa(final String complementoJustificativa) {
        this.complementoJustificativa = complementoJustificativa;
    }

    public String getAprovado() {
        return aprovado;
    }

    public void setAprovado(final String aprovado) {
        this.aprovado = aprovado;
    }

    public String getDescrSituacao() {
        descrSituacao = "";
        try {
            if (situacao != null) {
                descrSituacao = SituacaoEntregaItemRequisicao.valueOf(situacao.trim()).getDescricao();
            }
        } catch (final Exception e) {}
        return descrSituacao;
    }

    public void setDescrSituacao(final String descrSituacao) {
        this.descrSituacao = descrSituacao;
    }

    public String getTipoFornecedor() {
        return tipoFornecedor;
    }

    public void setTipoFornecedor(final String tipoFornecedor) {
        this.tipoFornecedor = tipoFornecedor;
    }

    public String getDescricaoFmtHtml() {
        descricaoFmtHtml = "";
        if (UtilStrings.isNotVazio(nomeFornecedor)) {
            descricaoFmtHtml += "<p><b>CPF/CNPJ Fornecedor:</b> ";
            if (UtilStrings.nullToVazio(tipoFornecedor).equalsIgnoreCase("J")) {
                descricaoFmtHtml += UtilFormatacao.formataCnpj(cpfCnpjFornecedor);
            } else {
                descricaoFmtHtml += UtilFormatacao.formataCpf(cpfCnpjFornecedor);
            }
            descricaoFmtHtml += "<br><b>Nome Fornecedor:</b> " + nomeFornecedor + "</p>";
        }
        if (UtilStrings.isNotVazio(numeroPedido)) {
            descricaoFmtHtml += "<p><b>Número do pedido:</b> " + numeroPedido + "</p>";
        }
        if (dataEntrega != null) {
            descricaoFmtHtml += "<p><b>Data da entrega:</b> " + UtilDatas.dateToSTR(dataEntrega) + "</p>";
        }
        return descricaoFmtHtml;
    }

    public void setDescricaoFmtHtml(final String descricaoFmtHtml) {
        this.descricaoFmtHtml = descricaoFmtHtml;
    }

}
