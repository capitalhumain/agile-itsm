package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class GrupoRecursosDTO extends BaseEntity {

    private Integer idGrupoRecurso;
    private String nomeGrupoRecurso;
    private String situacao;
    private String deleted;

    public Integer getIdGrupoRecurso() {
        return idGrupoRecurso;
    }

    public void setIdGrupoRecurso(final Integer parm) {
        idGrupoRecurso = parm;
    }

    public String getNomeGrupoRecurso() {
        return nomeGrupoRecurso;
    }

    public void setNomeGrupoRecurso(final String parm) {
        nomeGrupoRecurso = parm;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(final String parm) {
        situacao = parm;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(final String parm) {
        deleted = parm;
    }

}
