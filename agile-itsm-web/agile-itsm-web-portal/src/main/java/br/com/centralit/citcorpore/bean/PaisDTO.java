package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

@SuppressWarnings("serial")
public class PaisDTO extends BaseEntity {

	private Integer idPais;

	private String nomePais;

	/**
	 * @return the idPais
	 */
	public Integer getIdPais() {
		return idPais;
	}

	/**
	 * @param idPais
	 *            the idPais to set
	 */
	public void setIdPais(Integer idPais) {
		this.idPais = idPais;
	}

	/**
	 * @return the nomePais
	 */
	public String getNomePais() {
		return nomePais;
	}

	/**
	 * @param nomePais
	 *            the nomePais to set
	 */
	public void setNomePais(String nomePais) {
		this.nomePais = nomePais;
	}

}
