package br.com.centralit.citcorpore.bean;

import java.sql.Date;
import java.util.Collection;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.util.Enumerados.SituacaoPedidoCompra;

public class PedidoCompraDTO extends BaseEntity {

    private Integer idPedido;
    private Integer idCotacao;
    private Integer idEmpresa;
    private Integer idFornecedor;
    private Date dataPedido;
    private String numeroPedido;
    private String identificacaoEntrega;
    private Double valorFrete;
    private Double valorSeguro;
    private Double outrasDespesas;
    private String numeroNF;
    private Integer idEnderecoEntrega;
    private Date dataEntrega;
    private Date dataPrevistaEntrega;
    private String situacao;
    private String observacoes;

    private Integer idColetaPreco;
    private Integer idItemPedido;
    private Double quantidade;
    private String descrSituacao;

    private UsuarioDTO usuarioDto;

    private String nomeFornecedor;
    private Collection<UploadDTO> anexos;
    private Collection<ItemPedidoCompraDTO> colItens;
    private Collection<InspecaoPedidoCompraDTO> colInspecao;

    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(final Integer parm) {
        idPedido = parm;
    }

    public Integer getIdCotacao() {
        return idCotacao;
    }

    public void setIdCotacao(final Integer parm) {
        idCotacao = parm;
    }

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(final Integer parm) {
        idEmpresa = parm;
    }

    public Integer getIdFornecedor() {
        return idFornecedor;
    }

    public void setIdFornecedor(final Integer parm) {
        idFornecedor = parm;
    }

    public Date getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(final Date parm) {
        dataPedido = parm;
    }

    public String getNumeroPedido() {
        return numeroPedido;
    }

    public void setNumeroPedido(final String parm) {
        numeroPedido = parm;
    }

    public String getIdentificacaoEntrega() {
        return identificacaoEntrega;
    }

    public void setIdentificacaoEntrega(final String parm) {
        identificacaoEntrega = parm;
    }

    public Double getValorFrete() {
        return valorFrete;
    }

    public void setValorFrete(final Double parm) {
        valorFrete = parm;
    }

    public Double getValorSeguro() {
        return valorSeguro;
    }

    public void setValorSeguro(final Double parm) {
        valorSeguro = parm;
    }

    public Double getOutrasDespesas() {
        return outrasDespesas;
    }

    public void setOutrasDespesas(final Double parm) {
        outrasDespesas = parm;
    }

    public String getNumeroNF() {
        return numeroNF;
    }

    public void setNumeroNF(final String parm) {
        numeroNF = parm;
    }

    public Integer getIdEnderecoEntrega() {
        return idEnderecoEntrega;
    }

    public void setIdEnderecoEntrega(final Integer parm) {
        idEnderecoEntrega = parm;
    }

    public Date getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(final Date parm) {
        dataEntrega = parm;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(final String parm) {
        situacao = parm;
    }

    public String getNomeFornecedor() {
        return nomeFornecedor;
    }

    public void setNomeFornecedor(final String nomeFornecedor) {
        this.nomeFornecedor = nomeFornecedor;
    }

    public Collection<UploadDTO> getAnexos() {
        return anexos;
    }

    public void setAnexos(final Collection<UploadDTO> anexos) {
        this.anexos = anexos;
    }

    public Date getDataPrevistaEntrega() {
        return dataPrevistaEntrega;
    }

    public void setDataPrevistaEntrega(final Date dataPrevistaEntrega) {
        this.dataPrevistaEntrega = dataPrevistaEntrega;
    }

    public Integer getIdColetaPreco() {
        return idColetaPreco;
    }

    public void setIdColetaPreco(final Integer idColetaPreco) {
        this.idColetaPreco = idColetaPreco;
    }

    public Integer getIdItemPedido() {
        return idItemPedido;
    }

    public void setIdItemPedido(final Integer idItemPedido) {
        this.idItemPedido = idItemPedido;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(final Double quantidade) {
        this.quantidade = quantidade;
    }

    public Collection<ItemPedidoCompraDTO> getColItens() {
        return colItens;
    }

    public void setColItens(final Collection<ItemPedidoCompraDTO> colItens) {
        this.colItens = colItens;
    }

    public String getDescrSituacao() {
        descrSituacao = "";
        if (situacao != null) {
            descrSituacao = SituacaoPedidoCompra.valueOf(situacao).getDescricao();
        }
        return descrSituacao;
    }

    public void setDescrSituacao(final String descrSituacao) {
        this.descrSituacao = descrSituacao;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(final String observacoes) {
        this.observacoes = observacoes;
    }

    public Collection<InspecaoPedidoCompraDTO> getColInspecao() {
        return colInspecao;
    }

    public void setColInspecao(final Collection<InspecaoPedidoCompraDTO> colInspecao) {
        this.colInspecao = colInspecao;
    }

    public UsuarioDTO getUsuarioDto() {
        return usuarioDto;
    }

    public void setUsuarioDto(final UsuarioDTO usuarioDto) {
        this.usuarioDto = usuarioDto;
    }

}
