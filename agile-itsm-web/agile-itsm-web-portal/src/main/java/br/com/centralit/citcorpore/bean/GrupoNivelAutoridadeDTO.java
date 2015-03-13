package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class GrupoNivelAutoridadeDTO extends BaseEntity {

    private Integer idNivelAutoridade;
    private Integer idGrupo;

    private Integer sequencia;

    public Integer getIdNivelAutoridade() {
        return idNivelAutoridade;
    }

    public void setIdNivelAutoridade(final Integer parm) {
        idNivelAutoridade = parm;
    }

    public Integer getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(final Integer parm) {
        idGrupo = parm;
    }

    public Integer getSequencia() {
        return sequencia;
    }

    public void setSequencia(final Integer sequencia) {
        this.sequencia = sequencia;
    }

}
