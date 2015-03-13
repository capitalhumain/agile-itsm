package br.com.centralit.citcorpore.bean;

import java.sql.Timestamp;

import br.com.agileitsm.model.support.BaseEntity;

public class InspecaoPedidoCompraDTO extends BaseEntity {

    private Integer idPedido;
    private Integer idCriterio;
    private Timestamp dataHoraInspecao;
    private Integer idResponsavel;
    private String avaliacao;
    private String observacoes;

    private String tipoAvaliacao;
    private Integer sequencia;

    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(final Integer idPedido) {
        this.idPedido = idPedido;
    }

    public Integer getIdCriterio() {
        return idCriterio;
    }

    public void setIdCriterio(final Integer parm) {
        idCriterio = parm;
    }

    public Timestamp getDataHoraInspecao() {
        return dataHoraInspecao;
    }

    public void setDataHoraInspecao(final Timestamp parm) {
        dataHoraInspecao = parm;
    }

    public Integer getIdResponsavel() {
        return idResponsavel;
    }

    public void setIdResponsavel(final Integer parm) {
        idResponsavel = parm;
    }

    public String getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(final String parm) {
        avaliacao = parm;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(final String parm) {
        observacoes = parm;
    }

    public Integer getSequencia() {
        return sequencia;
    }

    public void setSequencia(final Integer sequencia) {
        this.sequencia = sequencia;
    }

    public String getTipoAvaliacao() {
        return tipoAvaliacao;
    }

    public void setTipoAvaliacao(final String tipoAvaliacao) {
        this.tipoAvaliacao = tipoAvaliacao;
    }

}
