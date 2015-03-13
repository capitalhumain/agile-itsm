package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class PagamentoProjetoDTO extends BaseEntity {

    private Integer idPagamentoProjeto;
    private Integer idProjeto;
    private java.sql.Date dataPagamento;
    private Double valorPagamento;
    private Double valorGlosa;
    private String detPagamento;
    private String situacao;
    private java.sql.Date dataUltAlteracao;
    private String horaUltAlteracao;
    private String usuarioUltAlteracao;

    private java.sql.Date dataPagamentoAtu;

    private Integer idMarcoPagamentoPrj;

    private Integer[] idTarefasParaPagamento;

    public Integer getIdPagamentoProjeto() {
        return idPagamentoProjeto;
    }

    public void setIdPagamentoProjeto(final Integer parm) {
        idPagamentoProjeto = parm;
    }

    public Integer getIdProjeto() {
        return idProjeto;
    }

    public void setIdProjeto(final Integer parm) {
        idProjeto = parm;
    }

    public java.sql.Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(final java.sql.Date parm) {
        dataPagamento = parm;
    }

    public Double getValorPagamento() {
        return valorPagamento;
    }

    public void setValorPagamento(final Double parm) {
        valorPagamento = parm;
    }

    public String getDetPagamento() {
        return detPagamento;
    }

    public void setDetPagamento(final String parm) {
        detPagamento = parm;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(final String parm) {
        situacao = parm;
    }

    public java.sql.Date getDataUltAlteracao() {
        return dataUltAlteracao;
    }

    public void setDataUltAlteracao(final java.sql.Date parm) {
        dataUltAlteracao = parm;
    }

    public String getHoraUltAlteracao() {
        return horaUltAlteracao;
    }

    public void setHoraUltAlteracao(final String parm) {
        horaUltAlteracao = parm;
    }

    public String getUsuarioUltAlteracao() {
        return usuarioUltAlteracao;
    }

    public void setUsuarioUltAlteracao(final String parm) {
        usuarioUltAlteracao = parm;
    }

    public Double getValorGlosa() {
        return valorGlosa;
    }

    public void setValorGlosa(final Double valorGlosa) {
        this.valorGlosa = valorGlosa;
    }

    public Integer[] getIdTarefasParaPagamento() {
        return idTarefasParaPagamento;
    }

    public void setIdTarefasParaPagamento(final Integer[] idTarefasParaPagamento) {
        this.idTarefasParaPagamento = idTarefasParaPagamento;
    }

    public Integer getIdMarcoPagamentoPrj() {
        return idMarcoPagamentoPrj;
    }

    public void setIdMarcoPagamentoPrj(final Integer idMarcoPagamentoPrj) {
        this.idMarcoPagamentoPrj = idMarcoPagamentoPrj;
    }

    public java.sql.Date getDataPagamentoAtu() {
        return dataPagamentoAtu;
    }

    public void setDataPagamentoAtu(final java.sql.Date dataPagamentoAtu) {
        this.dataPagamentoAtu = dataPagamentoAtu;
    }

}
