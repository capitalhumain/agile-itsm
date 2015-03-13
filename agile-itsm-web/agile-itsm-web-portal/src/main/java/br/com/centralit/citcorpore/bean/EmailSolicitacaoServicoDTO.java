package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class EmailSolicitacaoServicoDTO extends BaseEntity {

    private Integer idEmail;
    private String idMessage;
    private Integer idSolicitacao;
    private String origem;

    public Integer getIdEmail() {
        return idEmail;
    }

    public void setIdEmail(final Integer idEmail) {
        this.idEmail = idEmail;
    }

    public String getIdMessage() {
        return idMessage;
    }

    public void setIdMessage(final String idMessage) {
        this.idMessage = idMessage;
    }

    public Integer getIdSolicitacao() {
        return idSolicitacao;
    }

    public void setIdSolicitacao(final Integer idSolicitacao) {
        this.idSolicitacao = idSolicitacao;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(final String origem) {
        this.origem = origem;
    }

}
