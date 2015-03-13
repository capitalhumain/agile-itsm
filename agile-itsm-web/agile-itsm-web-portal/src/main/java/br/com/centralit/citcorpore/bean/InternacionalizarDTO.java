package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class InternacionalizarDTO extends BaseEntity {

    private Integer idInternacionalizacao;
    private String locale;

    public Integer getIdInternacionalizacao() {
        return idInternacionalizacao;
    }

    public void setIdInternacionalizacao(final Integer idInternacionalizacao) {
        this.idInternacionalizacao = idInternacionalizacao;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(final String locale) {
        this.locale = locale;
    }

}
