package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class MeuCatalogoDTO extends BaseEntity{
	/**
	 * 
	*/
	private static final long serialVersionUID = 638687400065001805L;
	private Integer idServico;
	private Integer idUsuario;
	
	public Integer getIdServico() {
		return idServico;
	}
	public void setIdServico(Integer idServico) {
		this.idServico = idServico;
	}
	public Integer getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

}
