package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class LimiteAprovacaoProcessoDTO extends BaseEntity {

    private Integer idLimiteAprovacao;
    private Integer idProcessoNegocio;

    public Integer getIdLimiteAprovacao() {
        return idLimiteAprovacao;
    }

    public void setIdLimiteAprovacao(final Integer parm) {
        idLimiteAprovacao = parm;
    }

    public Integer getIdProcessoNegocio() {
        return idProcessoNegocio;
    }

    public void setIdProcessoNegocio(final Integer parm) {
        idProcessoNegocio = parm;
    }

}
