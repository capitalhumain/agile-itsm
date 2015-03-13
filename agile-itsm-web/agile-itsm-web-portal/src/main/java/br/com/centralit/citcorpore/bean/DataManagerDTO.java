package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class DataManagerDTO extends BaseEntity {

    private Integer idObjetoNegocio;

    public Integer getIdObjetoNegocio() {
        return idObjetoNegocio;
    }

    public void setIdObjetoNegocio(final Integer idObjetoNegocio) {
        this.idObjetoNegocio = idObjetoNegocio;
    }

}
