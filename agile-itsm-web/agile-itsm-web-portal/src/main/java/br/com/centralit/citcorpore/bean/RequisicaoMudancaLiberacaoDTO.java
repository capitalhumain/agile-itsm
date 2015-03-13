package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class RequisicaoMudancaLiberacaoDTO extends BaseEntity {

    private Integer idRequisicaoMudancaLiberacao;
    private Integer idRequisicaoMudanca;
    private Integer idLiberacao;

    private String titulo;
    private String descricao;

    public Integer getIdRequisicaoMudancaLiberacao() {
        return idRequisicaoMudancaLiberacao;
    }

    public void setIdRequisicaoMudancaLiberacao(final Integer idRequisicaoMudancaLiberacao) {
        this.idRequisicaoMudancaLiberacao = idRequisicaoMudancaLiberacao;
    }

    public Integer getIdRequisicaoMudanca() {
        return idRequisicaoMudanca;
    }

    public void setIdRequisicaoMudanca(final Integer idRequisicaoMudanca) {
        this.idRequisicaoMudanca = idRequisicaoMudanca;
    }

    public Integer getIdLiberacao() {
        return idLiberacao;
    }

    public void setIdLiberacao(final Integer idLiberacao) {
        this.idLiberacao = idLiberacao;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(final String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(final String descricao) {
        this.descricao = descricao;
    }

}
