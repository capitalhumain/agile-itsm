package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class AgendaAtvPeriodicasDTO extends BaseEntity {

    private Long start;
    private Long end;
    private Integer idGrupoAtvPeriodica;
    private Integer idGrupoPesquisa;
    private Integer idEmpregado;

    public Long getStart() {
        return start;
    }

    public void setStart(final Long start) {
        this.start = start;
    }

    public Long getEnd() {
        return end;
    }

    public void setEnd(final Long end) {
        this.end = end;
    }

    public Integer getIdGrupoAtvPeriodica() {
        return idGrupoAtvPeriodica;
    }

    public void setIdGrupoAtvPeriodica(final Integer idGrupoAtvPeriodica) {
        this.idGrupoAtvPeriodica = idGrupoAtvPeriodica;
    }

    public Integer getIdGrupoPesquisa() {
        return idGrupoPesquisa;
    }

    public void setIdGrupoPesquisa(final Integer idGrupoPesquisa) {
        this.idGrupoPesquisa = idGrupoPesquisa;
    }

    public Integer getIdEmpregado() {
        return idEmpregado;
    }

    public void setIdEmpregado(final Integer idEmpregado) {
        this.idEmpregado = idEmpregado;
    }

}
