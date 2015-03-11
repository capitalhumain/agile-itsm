package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class AcessosPerfilSegurancaDTO extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1037812758934980332L;
	private Integer idPerfilSeguranca;
	private String path;
	
    public Integer getIdPerfilSeguranca() {
        return idPerfilSeguranca;
    }
    public void setIdPerfilSeguranca(Integer idPerfilSeguranca) {
        this.idPerfilSeguranca = idPerfilSeguranca;
    }
    public String getPath() {
        return path;
    }
    public void setPath(String path) {
        this.path = path;
    }
	
	
	
	
}
