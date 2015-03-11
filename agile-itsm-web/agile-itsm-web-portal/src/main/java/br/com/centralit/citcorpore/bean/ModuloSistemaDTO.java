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
	public void setIdModuloSistema(Integer idModuloSistema) {
		this.idModuloSistema = idModuloSistema;
	}
	public String getNomeModuloSistema() {
		return nomeModuloSistema;
	}
	public void setNomeModuloSistema(String nomeModuloSistema) {
		this.nomeModuloSistema = nomeModuloSistema;
	}

   

}