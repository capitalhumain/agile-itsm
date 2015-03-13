package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class ResponsavelCentroResultadoDTO extends BaseEntity {

    private Integer idResponsavel;
    private Integer idCentroResultado;
    private Integer[] idProcessoNegocio;
    private Integer sequencia;
    private String nomeEmpregado;

    private String idProcessoNegocioStr;

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

    public Integer[] getIdProcessoNegocio() {
        return idProcessoNegocio;
    }

    public void setIdProcessoNegocio(final Integer[] idProcessoNegocio) {
        this.idProcessoNegocio = idProcessoNegocio;
    }

    public Integer getSequencia() {
        return sequencia;
    }

    public void setSequencia(final Integer sequencia) {
        this.sequencia = sequencia;
    }

    public String getNomeEmpregado() {
        return nomeEmpregado;
    }

    public void setNomeEmpregado(final String nomeEmpregado) {
        this.nomeEmpregado = nomeEmpregado;
    }

    public String getIdProcessoNegocioStr() {
        return idProcessoNegocioStr;
    }

    public void setIdProcessoNegocioStr(final String idProcessoNegocioStr) {
        this.idProcessoNegocioStr = idProcessoNegocioStr;
    }

}
