package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class BICategoriasDTO extends BaseEntity {

    private Integer idCategoria;
    private String nomeCategoria;
    private String identificacao;
    private Integer idCategoriaPai;
    private String situacao;

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(final Integer parm) {
        idCategoria = parm;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public void setNomeCategoria(final String parm) {
        nomeCategoria = parm;
    }

    public String getIdentificacao() {
        return identificacao;
    }

    public void setIdentificacao(final String parm) {
        identificacao = parm;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(final String parm) {
        situacao = parm;
    }

    public Integer getIdCategoriaPai() {
        return idCategoriaPai;
    }

    public void setIdCategoriaPai(final Integer idCategoriaPai) {
        this.idCategoriaPai = idCategoriaPai;
    }

}
