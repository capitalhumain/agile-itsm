package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class ResumoProjetosDTO extends BaseEntity {

    private String condicaoProjeto;

    public String getCondicaoProjeto() {
        return condicaoProjeto;
    }

    public void setCondicaoProjeto(final String condicaoProjeto) {
        this.condicaoProjeto = condicaoProjeto;
    }

}
