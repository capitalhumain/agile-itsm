package br.com.centralit.citcorpore.metainfo.bean;

import java.util.ArrayList;
import java.util.Collection;

import br.com.agileitsm.model.support.BaseEntity;

public class LookupDTO extends BaseEntity {

    private Integer idCamposObjetoNegocio;
    private Integer idGrupoVisao;
    private String termoPesquisa;
    private String q;
    Collection values = new ArrayList<>();

    public String getQ() {
        return q;
    }

    public void setQ(final String q) {
        this.q = q;
    }

    public Collection getValues() {
        return values;
    }

    public void setValues(final Collection values) {
        this.values = values;
    }

    public Integer getIdCamposObjetoNegocio() {
        return idCamposObjetoNegocio;
    }

    public void setIdCamposObjetoNegocio(final Integer idCamposObjetoNegocio) {
        this.idCamposObjetoNegocio = idCamposObjetoNegocio;
    }

    public Integer getIdGrupoVisao() {
        return idGrupoVisao;
    }

    public void setIdGrupoVisao(final Integer idGrupoVisao) {
        this.idGrupoVisao = idGrupoVisao;
    }

    public String getTermoPesquisa() {
        return termoPesquisa;
    }

    public void setTermoPesquisa(final String termoPesquisa) {
        this.termoPesquisa = termoPesquisa;
    }

}
