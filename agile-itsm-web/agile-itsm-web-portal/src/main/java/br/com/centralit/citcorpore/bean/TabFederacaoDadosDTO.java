package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class TabFederacaoDadosDTO extends BaseEntity {

    private String nomeTabela;
    private String chaveFinal;
    private String chaveOriginal;
    private String origem;
    private java.sql.Timestamp criacao;
    private java.sql.Timestamp ultAtualiz;

    public String getNomeTabela() {
        return nomeTabela;
    }

    public void setNomeTabela(final String parm) {
        nomeTabela = parm;
    }

    public String getChaveFinal() {
        return chaveFinal;
    }

    public void setChaveFinal(final String parm) {
        chaveFinal = parm;
    }

    public String getChaveOriginal() {
        return chaveOriginal;
    }

    public void setChaveOriginal(final String parm) {
        chaveOriginal = parm;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(final String parm) {
        origem = parm;
    }

    public java.sql.Timestamp getCriacao() {
        return criacao;
    }

    public void setCriacao(final java.sql.Timestamp criacao) {
        this.criacao = criacao;
    }

    public java.sql.Timestamp getUltAtualiz() {
        return ultAtualiz;
    }

    public void setUltAtualiz(final java.sql.Timestamp ultAtualiz) {
        this.ultAtualiz = ultAtualiz;
    }

}
