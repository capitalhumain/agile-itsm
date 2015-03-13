package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class MatrizVisaoDTO extends BaseEntity {

    private Integer idMatriz;
    private Integer idVisao;
    private Integer idObjetoNegocio;
    private String nomeTabelaDB;
    private Integer idCamposObjetoNegocio1;
    private String nomeDB1;
    private Integer idCamposObjetoNegocio2;
    private String nomeDB2;
    private Integer idCamposObjetoNegocio3;
    private String nomeDB3;
    private String strInfo;
    private String nomeCampo1;
    private String nomeCampo2;
    private String nomeCampo3;
    private String descricaoCampo1;
    private String descricaoCampo2;
    private String descricaoCampo3;

    public Integer getIdMatriz() {
        return idMatriz;
    }

    public void setIdMatriz(final Integer parm) {
        idMatriz = parm;
    }

    public Integer getIdVisao() {
        return idVisao;
    }

    public void setIdVisao(final Integer parm) {
        idVisao = parm;
    }

    public Integer getIdObjetoNegocio() {
        return idObjetoNegocio;
    }

    public void setIdObjetoNegocio(final Integer parm) {
        idObjetoNegocio = parm;
    }

    public Integer getIdCamposObjetoNegocio1() {
        return idCamposObjetoNegocio1;
    }

    public void setIdCamposObjetoNegocio1(final Integer parm) {
        idCamposObjetoNegocio1 = parm;
    }

    public Integer getIdCamposObjetoNegocio2() {
        return idCamposObjetoNegocio2;
    }

    public void setIdCamposObjetoNegocio2(final Integer parm) {
        idCamposObjetoNegocio2 = parm;
    }

    public Integer getIdCamposObjetoNegocio3() {
        return idCamposObjetoNegocio3;
    }

    public void setIdCamposObjetoNegocio3(final Integer parm) {
        idCamposObjetoNegocio3 = parm;
    }

    public String getStrInfo() {
        return strInfo;
    }

    public void setStrInfo(final String parm) {
        strInfo = parm;
    }

    public String getNomeCampo1() {
        return nomeCampo1;
    }

    public void setNomeCampo1(final String parm) {
        nomeCampo1 = parm;
    }

    public String getNomeCampo2() {
        return nomeCampo2;
    }

    public void setNomeCampo2(final String parm) {
        nomeCampo2 = parm;
    }

    public String getNomeCampo3() {
        return nomeCampo3;
    }

    public void setNomeCampo3(final String parm) {
        nomeCampo3 = parm;
    }

    public String getDescricaoCampo1() {
        return descricaoCampo1;
    }

    public void setDescricaoCampo1(final String descricaoCampo1) {
        this.descricaoCampo1 = descricaoCampo1;
    }

    public String getDescricaoCampo2() {
        return descricaoCampo2;
    }

    public void setDescricaoCampo2(final String descricaoCampo2) {
        this.descricaoCampo2 = descricaoCampo2;
    }

    public String getDescricaoCampo3() {
        return descricaoCampo3;
    }

    public void setDescricaoCampo3(final String descricaoCampo3) {
        this.descricaoCampo3 = descricaoCampo3;
    }

    public String getNomeTabelaDB() {
        return nomeTabelaDB;
    }

    public void setNomeTabelaDB(final String nomeTabelaDB) {
        this.nomeTabelaDB = nomeTabelaDB;
    }

    public String getNomeDB1() {
        return nomeDB1;
    }

    public void setNomeDB1(final String nomeDB1) {
        this.nomeDB1 = nomeDB1;
    }

    public String getNomeDB2() {
        return nomeDB2;
    }

    public void setNomeDB2(final String nomeDB2) {
        this.nomeDB2 = nomeDB2;
    }

    public String getNomeDB3() {
        return nomeDB3;
    }

    public void setNomeDB3(final String nomeDB3) {
        this.nomeDB3 = nomeDB3;
    }

}
