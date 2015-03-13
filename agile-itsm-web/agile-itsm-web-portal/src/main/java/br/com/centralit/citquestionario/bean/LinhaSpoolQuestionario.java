package br.com.centralit.citquestionario.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class LinhaSpoolQuestionario extends BaseEntity {

    private static final long serialVersionUID = -8371985567775512010L;

    private String linha;
    private boolean generateTR;

    public LinhaSpoolQuestionario() {}

    public LinhaSpoolQuestionario(final String parmValorInicio) {
        linha = parmValorInicio;
    }

    public String getLinha() {
        return linha;
    }

    public void setLinha(final String linha) {
        this.linha = linha;
    }

    public boolean isGenerateTR() {
        return generateTR;
    }

    public void setGenerateTR(final boolean generateTR) {
        this.generateTR = generateTR;
    }

}
