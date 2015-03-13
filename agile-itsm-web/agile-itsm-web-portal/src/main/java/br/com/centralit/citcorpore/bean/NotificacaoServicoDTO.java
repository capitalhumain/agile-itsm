package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class NotificacaoServicoDTO extends BaseEntity {

    private Integer idNotificacao;

    private Integer idServico;

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
    public void setIdNotificacao(final Integer idNotificacao) {
        this.idNotificacao = idNotificacao;
    }

    /**
     * @return the idServico
     */
    public Integer getIdServico() {
        return idServico;
    }

    /**
     * @param idServico
     *            the idServico to set
     */
    public void setIdServico(final Integer idServico) {
        this.idServico = idServico;
    }

}
