package br.com.centralit.citcorpore.metainfo.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class DadosAdicionaisDTO extends BaseEntity {

    private String nome;
    private String[] dados;

    public String getNome() {
        return nome;
    }

    public void setNome(final String nome) {
        this.nome = nome;
    }

    public String[] getDados() {
        return dados;
    }

    public void setDados(final String[] dados) {
        this.dados = dados;
    }

}
