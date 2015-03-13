package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class DelegacaoCentroResultadoFluxoDTO extends BaseEntity {

    private Integer idDelegacaoCentroResultado;
    private Integer idInstanciaFluxo;

    public Integer getIdDelegacaoCentroResultado() {
        return idDelegacaoCentroResultado;
    }

    public void setIdDelegacaoCentroResultado(final Integer parm) {
        idDelegacaoCentroResultado = parm;
    }

    public Integer getIdInstanciaFluxo() {
        return idInstanciaFluxo;
    }

    public void setIdInstanciaFluxo(final Integer parm) {
        idInstanciaFluxo = parm;
    }

}
