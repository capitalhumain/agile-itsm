package br.com.centralit.citcorpore.bean;

import java.sql.Date;

import br.com.agileitsm.model.support.BaseEntity;

public class RecursoAvaliacaoDTO extends BaseEntity {

    private Date dataInicio;
    private Date dataFim;
    private Integer idGrupoRecurso;

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(final Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(final Date dataFim) {
        this.dataFim = dataFim;
    }

    public Integer getIdGrupoRecurso() {
        return idGrupoRecurso;
    }

    public void setIdGrupoRecurso(final Integer idGrupoRecurso) {
        this.idGrupoRecurso = idGrupoRecurso;
    }

}
