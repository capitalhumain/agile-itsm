package br.com.centralit.citcorpore.metainfo.bean;

import java.util.Collection;

import br.com.agileitsm.model.support.BaseEntity;

public class ObjetoNegocioDTO extends BaseEntity {

    private Integer idObjetoNegocio;
    private String nomeObjetoNegocio;
    private String nomeTabelaDB;
    private String situacao;

    private Collection colCampos;

    public Integer getIdObjetoNegocio() {
        return idObjetoNegocio;
    }

    public void setIdObjetoNegocio(final Integer parm) {
        idObjetoNegocio = parm;
    }

    public String getNomeObjetoNegocio() {
        return nomeObjetoNegocio;
    }

    public void setNomeObjetoNegocio(final String parm) {
        nomeObjetoNegocio = parm;
    }

    public String getNomeTabelaDB() {
        return nomeTabelaDB;
    }

    public void setNomeTabelaDB(final String parm) {
        nomeTabelaDB = parm;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(final String parm) {
        situacao = parm;
    }

    public Collection getColCampos() {
        return colCampos;
    }

    public void setColCampos(final Collection colCampos) {
        this.colCampos = colCampos;
    }

}
