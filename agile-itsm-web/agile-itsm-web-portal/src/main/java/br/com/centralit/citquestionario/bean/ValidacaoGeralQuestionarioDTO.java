package br.com.centralit.citquestionario.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class ValidacaoGeralQuestionarioDTO extends BaseEntity {

    private static final long serialVersionUID = -581444197536445647L;
    private String validacao;

    public String getValidacao() {
        if (validacao == null) {
            validacao = "";
        }
        return validacao;
    }

    public void setValidacao(final String validacao) {
        this.validacao = validacao;
    }

    public void addValidacao(final String validacaoParm) {
        if (validacao == null) {
            validacao = "";
        }
        validacao += validacaoParm;
    }

}
