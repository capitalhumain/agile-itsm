package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class NotificacaoGrupoDTO extends BaseEntity {

    private Integer idNotificacao;

    private Integer idGrupo;

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
     * @return the idGrupo
     */
    public Integer getIdGrupo() {
        return idGrupo;
    }

    /**
     * @param idGrupo
     *            the idGrupo to set
     */
    public void setIdGrupo(final Integer idGrupo) {
        this.idGrupo = idGrupo;
    }

}
