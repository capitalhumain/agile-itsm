package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class LimiteAprovacaoAutoridadeDTO extends BaseEntity {

    private Integer idLimiteAprovacao;
    private Integer idNivelAutoridade;

    public Integer getIdLimiteAprovacao() {
        return idLimiteAprovacao;
    }

    public void setIdLimiteAprovacao(final Integer parm) {
        idLimiteAprovacao = parm;
    }

    public Integer getIdNivelAutoridade() {
        return idNivelAutoridade;
    }

    public void setIdNivelAutoridade(final Integer parm) {
        idNivelAutoridade = parm;
    }

}
