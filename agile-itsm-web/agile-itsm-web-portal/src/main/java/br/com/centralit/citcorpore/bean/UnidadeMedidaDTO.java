package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class UnidadeMedidaDTO extends BaseEntity {

    private Integer idUnidadeMedida;
    private String nomeUnidadeMedida;
    private String siglaUnidadeMedida;
    private String situacao;

    public Integer getIdUnidadeMedida() {
        return idUnidadeMedida;
    }

    public void setIdUnidadeMedida(final Integer parm) {
        idUnidadeMedida = parm;
    }

    public String getNomeUnidadeMedida() {
        return nomeUnidadeMedida;
    }

    public void setNomeUnidadeMedida(final String parm) {
        nomeUnidadeMedida = parm;
    }

    public String getSiglaUnidadeMedida() {
        return siglaUnidadeMedida;
    }

    public void setSiglaUnidadeMedida(final String parm) {
        siglaUnidadeMedida = parm;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(final String parm) {
        situacao = parm;
    }

}
