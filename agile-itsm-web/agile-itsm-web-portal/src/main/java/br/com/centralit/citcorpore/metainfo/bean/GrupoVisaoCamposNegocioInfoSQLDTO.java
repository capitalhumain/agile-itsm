package br.com.centralit.citcorpore.metainfo.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class GrupoVisaoCamposNegocioInfoSQLDTO extends BaseEntity {

    private Integer idGrupoVisaoCamposNegocioInfoSQL;
    private Integer idGrupoVisao;
    private Integer idCamposObjetoNegocio;
    private String campo;
    private String tipoLigacao;
    private String filtro;
    private String descricao;

    public Integer getIdGrupoVisaoCamposNegocioInfoSQL() {
        return idGrupoVisaoCamposNegocioInfoSQL;
    }

    public void setIdGrupoVisaoCamposNegocioInfoSQL(final Integer parm) {
        idGrupoVisaoCamposNegocioInfoSQL = parm;
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

    public String getCampo() {
        return campo;
    }

    public void setCampo(final String parm) {
        campo = parm;
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
