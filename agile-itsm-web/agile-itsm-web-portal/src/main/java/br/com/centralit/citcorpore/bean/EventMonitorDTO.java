package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class EventMonitorDTO extends BaseEntity {

    private Integer idGrupoRecurso;
    private String nomeGrupoRecurso;

    public Integer getIdGrupoRecurso() {
        return idGrupoRecurso;
    }

    public void setIdGrupoRecurso(final Integer idGrupoRecurso) {
        this.idGrupoRecurso = idGrupoRecurso;
    }

    public String getNomeGrupoRecurso() {
        return nomeGrupoRecurso;
    }

    public void setNomeGrupoRecurso(final String nomeGrupoRecurso) {
        this.nomeGrupoRecurso = nomeGrupoRecurso;
    }

}
