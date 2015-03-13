package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class DesignerWorkflowDTO extends BaseEntity {

    private String nome;
    private String type;
    private Integer numero;

    private Integer numeroDecisoes;

    public String getNome() {
        return nome;
    }

    public void setNome(final String nome) {
        this.nome = nome;
    }

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(final Integer numero) {
        this.numero = numero;
    }

    public Integer getNumeroDecisoes() {
        return numeroDecisoes;
    }

    public void setNumeroDecisoes(final Integer numeroDecisoes) {
        this.numeroDecisoes = numeroDecisoes;
    }

}
