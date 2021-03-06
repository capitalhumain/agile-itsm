package br.com.centralit.citcorpore.metainfo.bean;

import java.util.ArrayList;
import java.util.Collection;

import br.com.agileitsm.model.support.BaseEntity;

public class TableSearchDTO extends BaseEntity {

    private Integer idVisao;
    private Integer idVisaoRelacionada;
    private Integer idCamposObjetoNegocio;
    private Integer idGrupoVisao;
    private String sort;
    private String order;
    private String sSearch;
    private Integer iSortCol_0;
    private String sSortDir_0;

    private Integer rows;
    private String load;
    private String matriz;
    private String jsonData;

    Collection values = new ArrayList<>();

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

    public Integer getIdVisao() {
        return idVisao;
    }

    public void setIdVisao(final Integer idVisao) {
        this.idVisao = idVisao;
    }

    public String getSSearch() {
        return sSearch;
    }

    public void setSSearch(final String sSearch) {
        this.sSearch = sSearch;
    }

    public Integer getISortCol_0() {
        return iSortCol_0;
    }

    public void setISortCol_0(final Integer iSortCol_0) {
        this.iSortCol_0 = iSortCol_0;
    }

    public String getSSortDir_0() {
        return sSortDir_0;
    }

    public void setSSortDir_0(final String sSortDir_0) {
        this.sSortDir_0 = sSortDir_0;
    }

    public String getLoad() {
        return load;
    }

    public void setLoad(final String load) {
        this.load = load;
    }

    public String getJsonData() {
        return jsonData;
    }

    public void setJsonData(final String jsonData) {
        this.jsonData = jsonData;
    }

    public Integer getIdVisaoRelacionada() {
        return idVisaoRelacionada;
    }

    public void setIdVisaoRelacionada(final Integer idVisaoRelacionada) {
        this.idVisaoRelacionada = idVisaoRelacionada;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(final String sort) {
        this.sort = sort;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(final String order) {
        this.order = order;
    }

    public String getMatriz() {
        return matriz;
    }

    public void setMatriz(final String matriz) {
        this.matriz = matriz;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(final Integer rows) {
        this.rows = rows;
    }

}
