package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

@SuppressWarnings("serial")
public class NotificacaoUsuarioDTO extends BaseEntity {

	private Integer idNotificacao;

	private Integer idUsuario;

	/**
	 * @return the idNotificacao
	 */
	public Integer getIdNotificacao() {
		return idNotificacao;
	}

	/**
	 * @param idNotificacao
	 *            the idNotificacao to set
	 */
	public void setIdNotificacao(Integer idNotificacao) {
		this.idNotificacao = idNotificacao;
	}

	/**
	 * @return the idUsuario
	 */
	public Integer getIdUsuario() {
		return idUsuario;
	}

	/**
	 * @param idUsuario
	 *            the idUsuario to set
	 */
	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

}
