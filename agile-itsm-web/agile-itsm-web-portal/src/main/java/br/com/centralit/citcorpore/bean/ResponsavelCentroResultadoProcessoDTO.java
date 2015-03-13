package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class ResponsavelCentroResultadoProcessoDTO extends BaseEntity {

    private Integer idResponsavel;
    private Integer idCentroResultado;
    private Integer idProcessoNegocio;

    public Integer getIdResponsavel() {
        return idResponsavel;
    }

    public void setIdResponsavel(final Integer parm) {
        idResponsavel = parm;
    }

    public Integer getIdCentroResultado() {
        return idCentroResultado;
    }

    public void setIdCentroResultado(final Integer parm) {
        idCentroResultado = parm;
    }

    public Integer getIdProcessoNegocio() {
        return idProcessoNegocio;
    }

    public void setIdProcessoNegocio(final Integer parm) {
        idProcessoNegocio = parm;
    }

}
