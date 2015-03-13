package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

/**
 * @author Pedro
 *
 */
public class ControleContratoModuloSistemaDTO extends BaseEntity {

    private Integer idModuloSistema;
    private Integer idControleContrato;

    public Integer getIdModuloSistema() {
        return idModuloSistema;
    }

    public void setIdModuloSistema(final Integer idModuloSistema) {
        this.idModuloSistema = idModuloSistema;
    }

    public Integer getIdControleContrato() {
        return idControleContrato;
    }

    public void setIdControleContrato(final Integer idControleContrato) {
        this.idControleContrato = idControleContrato;
    }

}
