package br.com.centralit.citcorpore.bean;

import java.sql.Timestamp;

import br.com.agileitsm.model.support.BaseEntity;

public class ParecerDTO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Integer idParecer;
    private Integer idAlcada;
    private Integer idJustificativa;
    private Integer idResponsavel;
    private Timestamp dataHoraParecer;
    private String complementoJustificativa;
    private String aprovado;
    private String observacoes;

    public Integer getIdParecer() {
        return idParecer;
    }

    public void setIdParecer(final Integer parm) {
        idParecer = parm;
    }

    public Integer getIdAlcada() {
        return idAlcada;
    }

    public void setIdAlcada(final Integer parm) {
        idAlcada = parm;
    }

    public Integer getIdJustificativa() {
        return idJustificativa;
    }

    public void setIdJustificativa(final Integer parm) {
        idJustificativa = parm;
    }

    public Integer getIdResponsavel() {
        return idResponsavel;
    }

    public void setIdResponsavel(final Integer parm) {
        idResponsavel = parm;
    }

    public Timestamp getDataHoraParecer() {
        return dataHoraParecer;
    }

    public void setDataHoraParecer(final Timestamp parm) {
        dataHoraParecer = parm;
    }

    public String getComplementoJustificativa() {
        return complementoJustificativa;
    }

    public void setComplementoJustificativa(final String parm) {
        complementoJustificativa = parm;
    }

    public String getAprovado() {
        return aprovado;
    }

    public void setAprovado(final String aprovado) {
        this.aprovado = aprovado;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(final String observacoes) {
        this.observacoes = observacoes;
    }

}
