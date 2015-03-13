package br.com.centralit.citcorpore.metainfo.bean;

import java.util.Collection;

import br.com.agileitsm.model.support.BaseEntity;

public class GrupoVisaoDTO extends BaseEntity {

    private Integer idGrupoVisao;
    private Integer idVisao;
    private String descricaoGrupoVisao;
    private String forma;
    private Integer ordem;
    private String situacao;

    private Collection colCamposVisao;

    public Integer getIdGrupoVisao() {
        return idGrupoVisao;
    }

    public void setIdGrupoVisao(final Integer parm) {
        idGrupoVisao = parm;
    }

    public Integer getIdVisao() {
        return idVisao;
    }

    public void setIdVisao(final Integer parm) {
        idVisao = parm;
    }

    public String getDescricaoGrupoVisao() {
        return descricaoGrupoVisao;
    }

    public void setDescricaoGrupoVisao(final String parm) {
        descricaoGrupoVisao = parm;
    }

    public String getForma() {
        return forma;
    }

    public void setForma(final String parm) {
        forma = parm;
    }

    public Integer getOrdem() {
        return ordem;
    }

    public void setOrdem(final Integer parm) {
        ordem = parm;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(final String parm) {
        situacao = parm;
    }

    public Collection getColCamposVisao() {
        return colCamposVisao;
    }

    public void setColCamposVisao(final Collection colCamposVisao) {
        this.colCamposVisao = colCamposVisao;
    }

}
