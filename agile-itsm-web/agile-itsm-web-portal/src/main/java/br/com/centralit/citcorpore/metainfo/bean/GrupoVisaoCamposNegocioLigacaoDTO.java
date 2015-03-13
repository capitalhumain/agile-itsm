package br.com.centralit.citcorpore.metainfo.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class GrupoVisaoCamposNegocioLigacaoDTO extends BaseEntity {

    public static String PRESENTATION = "A";
    public static String VALUE = "V";
    public static String FILTER = "F";
    public static String ORDER = "O";

    private Integer idGrupoVisaoCamposNegocioLigacao;
    private Integer idGrupoVisao;
    private Integer idCamposObjetoNegocio;
    private Integer idCamposObjetoNegocioLigacao;
    private String tipoLigacao;
    private String filtro;
    private String descricao;

    public Integer getIdGrupoVisaoCamposNegocioLigacao() {
        return idGrupoVisaoCamposNegocioLigacao;
    }

    public void setIdGrupoVisaoCamposNegocioLigacao(final Integer parm) {
        idGrupoVisaoCamposNegocioLigacao = parm;
    }

    public Integer getIdGrupoVisao() {
        return idGrupoVisao;
    }

    public void setIdGrupoVisao(final Integer parm) {
        idGrupoVisao = parm;
    }

    public Integer getIdCamposObjetoNegocio() {
        return idCamposObjetoNegocio;
    }

    public void setIdCamposObjetoNegocio(final Integer parm) {
        idCamposObjetoNegocio = parm;
    }

    public Integer getIdCamposObjetoNegocioLigacao() {
        return idCamposObjetoNegocioLigacao;
    }

    public void setIdCamposObjetoNegocioLigacao(final Integer parm) {
        idCamposObjetoNegocioLigacao = parm;
    }

    public String getTipoLigacao() {
        return tipoLigacao;
    }

    public void setTipoLigacao(final String parm) {
        tipoLigacao = parm;
    }

    public String getFiltro() {
        return filtro;
    }

    public void setFiltro(final String parm) {
        filtro = parm;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(final String descricao) {
        this.descricao = descricao;
    }

}
