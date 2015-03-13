package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

/**
 * @author Pedro
 *
 */
public class ModuloSistemaDTO extends BaseEntity {

    private Integer idModuloSistema;
    private String nomeModuloSistema;

    public Integer getIdModuloSistema() {
        return idModuloSistema;
    }

    public void setIdModuloSistema(final Integer idModuloSistema) {
        this.idModuloSistema = idModuloSistema;
    }

    public String getNomeModuloSistema() {
        return nomeModuloSistema;
    }

    public void setNomeModuloSistema(final String nomeModuloSistema) {
        this.nomeModuloSistema = nomeModuloSistema;
    }

}
