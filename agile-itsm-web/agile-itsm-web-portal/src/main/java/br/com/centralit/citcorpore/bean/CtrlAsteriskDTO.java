package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class CtrlAsteriskDTO extends BaseEntity {

	private static final long serialVersionUID = 4964912748878635243L;

	private String ramalTelefone;
	private String listaChamadas;
	
	public String getRamalTelefone() {
		return ramalTelefone;
	}
	public void setRamalTelefone(String ramalTelefone) {
		this.ramalTelefone = ramalTelefone;
	}
	public String getListaChamadas() {
		return listaChamadas;
	}
	public void setListaChamadas(String listaChamadas) {
		this.listaChamadas = listaChamadas;
	}
}
