package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class TipoSoftwareDTO extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3326603135917156302L;
	
	private Integer idTipoSoftware;
	private String nome;
	

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Integer getIdTipoSoftware() {
		return idTipoSoftware;
	}
	public void setIdTipoSoftware(Integer idTipoSoftware) {
		this.idTipoSoftware = idTipoSoftware;
	}
	

}
