package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class GrupoAtvPeriodicaDTO extends BaseEntity {

    private Integer idGrupoAtvPeriodica;
    private String nomeGrupoAtvPeriodica;
    private String descGrupoAtvPeriodica;
    private String deleted;
    private java.sql.Date dataInicio;
    private java.sql.Date dataFim;

    public Integer getIdGrupoAtvPeriodica() {
        return idGrupoAtvPeriodica;
    }

    public void setIdGrupoAtvPeriodica(final Integer parm) {
        idGrupoAtvPeriodica = parm;
    }

    public String getNomeGrupoAtvPeriodica() {
        return nomeGrupoAtvPeriodica;
    }

    public void setNomeGrupoAtvPeriodica(final String parm) {
        nomeGrupoAtvPeriodica = parm;
    }

    public String getDescGrupoAtvPeriodica() {
        return descGrupoAtvPeriodica;
    }

    public void setDescGrupoAtvPeriodica(final String parm) {
        descGrupoAtvPeriodica = parm;
    }

    public java.sql.Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(final java.sql.Date parm) {
        dataInicio = parm;
    }

    public java.sql.Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(final java.sql.Date parm) {
        dataFim = parm;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(final String deleted) {
        this.deleted = deleted;
    }

}
