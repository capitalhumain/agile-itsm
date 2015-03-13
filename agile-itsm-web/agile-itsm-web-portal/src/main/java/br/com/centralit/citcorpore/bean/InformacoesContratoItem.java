package br.com.centralit.citcorpore.bean;

import java.io.Serializable;
import java.util.Collection;

public class InformacoesContratoItem implements Serializable {

    private static final long serialVersionUID = 7915036904228171712L;

    private String nome;
    private String descricao;
    private String path;
    private boolean grupo;
    private Integer idQuestionario;
    private String funcItem;
    private Collection colSubItens;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(final String descricao) {
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(final String nome) {
        this.nome = nome;
    }

    public String getPath() {
        return path;
    }

    public void setPath(final String path) {
        this.path = path;
    }

    public Collection getColSubItens() {
        return colSubItens;
    }

    public void setColSubItens(final Collection colSubItens) {
        this.colSubItens = colSubItens;
    }

    public boolean isGrupo() {
        return grupo;
    }

    public void setGrupo(final boolean grupo) {
        this.grupo = grupo;
    }

    public Integer getIdQuestionario() {
        return idQuestionario;
    }

    public void setIdQuestionario(final Integer idQuestionario) {
        this.idQuestionario = idQuestionario;
    }

    public String getFuncItem() {
        return funcItem;
    }

    public void setFuncItem(final String funcItem) {
        this.funcItem = funcItem;
    }

}
