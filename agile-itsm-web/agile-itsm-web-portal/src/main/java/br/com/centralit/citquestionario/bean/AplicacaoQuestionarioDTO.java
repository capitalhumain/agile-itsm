package br.com.centralit.citquestionario.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class AplicacaoQuestionarioDTO extends BaseEntity {

    private static final long serialVersionUID = 2984986456849222230L;

    private Integer idAplicacaoQuestionario;
    private Integer idQuestionario;
    private Integer idTipoProduto;
    private String situacao;
    private String aplicacao;

    public Integer getIdAplicacaoQuestionario() {
        return idAplicacaoQuestionario;
    }

    public void setIdAplicacaoQuestionario(final Integer idAplicacaoQuestionario) {
        this.idAplicacaoQuestionario = idAplicacaoQuestionario;
    }

    public Integer getIdQuestionario() {
        return idQuestionario;
    }

    public void setIdQuestionario(final Integer idQuestionario) {
        this.idQuestionario = idQuestionario;
    }

    public Integer getIdTipoProduto() {
        return idTipoProduto;
    }

    public void setIdTipoProduto(final Integer idTipoProduto) {
        this.idTipoProduto = idTipoProduto;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(final String situacao) {
        this.situacao = situacao;
    }

    public String getAplicacao() {
        return aplicacao;
    }

    public void setAplicacao(final String aplicacao) {
        this.aplicacao = aplicacao;
    }

}
