package br.com.centralit.citquestionario.bean;

import java.util.Collection;

import br.com.agileitsm.model.support.BaseEntity;

public class ListagemDTO extends BaseEntity {

    private static final long serialVersionUID = -832399751420919436L;

    private String nome;
    private String descricao;
    private String SQL;
    private Collection campos;
    private Collection linhas;

    public String getNome() {
        return nome;
    }

    public void setNome(final String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(final String descricao) {
        this.descricao = descricao;
    }

    public String getSQL() {
        return SQL;
    }

    public void setSQL(final String sql) {
        SQL = sql;
    }

    public Collection getCampos() {
        return campos;
    }

    public void setCampos(final Collection campos) {
        this.campos = campos;
    }

    public Collection getLinhas() {
        return linhas;
    }

    public void setLinhas(final Collection linhas) {
        this.linhas = linhas;
    }

}
