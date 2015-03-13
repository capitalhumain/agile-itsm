package br.com.centralit.citcorpore.bean;

import java.sql.Timestamp;

import br.com.agileitsm.model.support.BaseEntity;

public class InspecaoEntregaItemDTO extends BaseEntity {

    private Integer idEntrega;
    private Integer idCriterio;
    private Timestamp dataHoraInspecao;
    private Integer idResponsavel;
    private String avaliacao;
    private String observacoes;

    private String tipoAvaliacao;

    private Integer sequencia;

    public Integer getIdEntrega() {
        return idEntrega;
    }

    public void setIdEntrega(final Integer parm) {
        idEntrega = parm;
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
