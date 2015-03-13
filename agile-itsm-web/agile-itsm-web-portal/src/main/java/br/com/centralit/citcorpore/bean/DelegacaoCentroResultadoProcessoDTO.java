package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class DelegacaoCentroResultadoProcessoDTO extends BaseEntity {

    private Integer idDelegacaoCentroResultado;
    private Integer idProcessoNegocio;

    public Integer getIdDelegacaoCentroResultado() {
        return idDelegacaoCentroResultado;
    }

    public void setIdDelegacaoCentroResultado(final Integer parm) {
        idDelegacaoCentroResultado = parm;
    }

    public Integer getIdProcessoNegocio() {
        return idProcessoNegocio;
    }

    public void setIdProcessoNegocio(final Integer parm) {
        idProcessoNegocio = parm;
    }

}
