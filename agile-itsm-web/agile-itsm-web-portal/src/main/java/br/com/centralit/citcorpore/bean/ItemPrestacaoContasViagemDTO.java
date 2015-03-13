package br.com.centralit.citcorpore.bean;

import java.sql.Date;

import br.com.agileitsm.model.support.BaseEntity;

public class ItemPrestacaoContasViagemDTO extends BaseEntity {

    private Integer idItemPrestContasViagem;
    private Integer idPrestacaoContasViagem;
    private Integer idItemDespesaViagem;
    private Integer idFornecedor;
    private Date data;
    private String nomeFornecedor;
    private String numeroDocumento;
    private String descricao;
    private Double valor;
    private String valorAux;

    private static final long serialVersionUID = 1L;

    public Integer getIdItemPrestContasViagem() {
        return idItemPrestContasViagem;
    }

    public void setIdItemPrestContasViagem(final Integer idItemPrestContasViagem) {
        this.idItemPrestContasViagem = idItemPrestContasViagem;
    }

    public Integer getIdPrestacaoContasViagem() {
        return idPrestacaoContasViagem;
    }

    public void setIdPrestacaoContasViagem(final Integer idPrestacaoContasViagem) {
        this.idPrestacaoContasViagem = idPrestacaoContasViagem;
    }

    public Integer getIdItemDespesaViagem() {
        return idItemDespesaViagem;
    }

    public void setIdItemDespesaViagem(final Integer idItemDespesaViagem) {
        this.idItemDespesaViagem = idItemDespesaViagem;
    }

    public Integer getIdFornecedor() {
        return idFornecedor;
    }

    public void setIdFornecedor(final Integer idFornecedor) {
        this.idFornecedor = idFornecedor;
    }

    public Date getData() {
        return data;
    }

    public void setData(final Date data) {
        this.data = data;
    }

    public String getNomeFornecedor() {
        return nomeFornecedor;
    }

    public void setNomeFornecedor(final String nomeFornecedor) {
        this.nomeFornecedor = nomeFornecedor;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(final String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(final String descricao) {
        this.descricao = descricao;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(final Double valor) {
        this.valor = valor;
        valorAux = valor.toString();
    }

    public String getValorAux() {
        return valorAux;
    }

    public void setValorAux(final String valorAux) {
        this.valorAux = valorAux;
    }

}
