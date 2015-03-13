package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class AvaliacaoColetaPrecoDTO extends BaseEntity {

    private Integer idCriterio;
    private Integer idColetaPreco;
    private Integer avaliacao;

    public Integer getIdCriterio() {
        return idCriterio;
    }

    public void setIdCriterio(final Integer parm) {
        idCriterio = parm;
    }

    public Integer getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(final Integer parm) {
        avaliacao = parm;
    }

    public Integer getIdColetaPreco() {
        return idColetaPreco;
    }

    public void setIdColetaPreco(final Integer idColetaPreco) {
        this.idColetaPreco = idColetaPreco;
    }

}
