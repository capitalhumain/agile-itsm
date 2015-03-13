package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class CriterioCotacaoCategoriaDTO extends BaseEntity {

    private Integer idCategoria;
    private Integer idCriterio;
    private Integer pesoCotacao;

    private Integer sequencia;

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(final Integer parm) {
        idCategoria = parm;
    }

    public Integer getIdCriterio() {
        return idCriterio;
    }

    public void setIdCriterio(final Integer parm) {
        idCriterio = parm;
    }

    public Integer getPesoCotacao() {
        return pesoCotacao;
    }

    public void setPesoCotacao(final Integer parm) {
        pesoCotacao = parm;
    }

    public Integer getSequencia() {
        return sequencia;
    }

    public void setSequencia(final Integer sequencia) {
        this.sequencia = sequencia;
    }

}
