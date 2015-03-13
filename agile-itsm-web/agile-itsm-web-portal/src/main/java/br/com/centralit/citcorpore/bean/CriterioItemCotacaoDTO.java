package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class CriterioItemCotacaoDTO extends BaseEntity {

    private Integer idCriterio;
    private Integer idItemCotacao;
    private Integer peso;

    private Integer sequencia;

    public Integer getIdCriterio() {
        return idCriterio;
    }

    public void setIdCriterio(final Integer parm) {
        idCriterio = parm;
    }

    public Integer getIdItemCotacao() {
        return idItemCotacao;
    }

    public void setIdItemCotacao(final Integer idItemCotacao) {
        this.idItemCotacao = idItemCotacao;
    }

    public Integer getPeso() {
        return peso;
    }

    public void setPeso(final Integer parm) {
        peso = parm;
    }

    public Integer getSequencia() {
        return sequencia;
    }

    public void setSequencia(final Integer sequencia) {
        this.sequencia = sequencia;
    }

}
