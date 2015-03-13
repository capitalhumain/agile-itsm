package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class ControleQuestionariosDTO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Integer idControleQuestionario;

    public Integer getIdControleQuestionario() {
        return idControleQuestionario;
    }

    public void setIdControleQuestionario(final Integer idControleQuestionario) {
        this.idControleQuestionario = idControleQuestionario;
    }

}
