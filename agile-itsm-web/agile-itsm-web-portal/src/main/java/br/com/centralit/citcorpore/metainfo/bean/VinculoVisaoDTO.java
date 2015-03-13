package br.com.centralit.citcorpore.metainfo.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class VinculoVisaoDTO extends BaseEntity {

    public static String VINCULO_1_TO_N = "1";
    public static String VINCULO_N_TO_N = "2";

    private Integer idVisaoRelacionada;
    private Integer seq;
    private String tipoVinculo;
    private Integer idGrupoVisaoPai;
    private Integer idGrupoVisaoFilho;
    private Integer idCamposObjetoNegocioPai;
    private Integer idCamposObjetoNegocioFilho;
    private Integer idCamposObjetoNegocioPaiNN;
    private Integer idCamposObjetoNegocioFilhoNN;
    private String nomeTabelaPai;
    private String nomeTabelaPaiNN;
    private String nomeTabelaFilho;
    private String nomeTabelaFilhoNN;

    private String nomeDBPai;
    private String nomeDBPaiNN;
    private String nomeDBPaiFilho;
    private String nomeDBPaiFilhoNN;

    private String controle;

    public Integer getIdVisaoRelacionada() {
        return idVisaoRelacionada;
    }

    public void setIdVisaoRelacionada(final Integer parm) {
        idVisaoRelacionada = parm;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(final Integer parm) {
        seq = parm;
    }

    public String getTipoVinculo() {
        return tipoVinculo;
    }

    public void setTipoVinculo(final String parm) {
        tipoVinculo = parm;
    }

    public Integer getIdGrupoVisaoPai() {
        return idGrupoVisaoPai;
    }

    public void setIdGrupoVisaoPai(final Integer parm) {
        idGrupoVisaoPai = parm;
    }

    public Integer getIdCamposObjetoNegocioPai() {
        return idCamposObjetoNegocioPai;
    }

    public void setIdCamposObjetoNegocioPai(final Integer parm) {
        idCamposObjetoNegocioPai = parm;
    }

    public Integer getIdGrupoVisaoFilho() {
        return idGrupoVisaoFilho;
    }

    public void setIdGrupoVisaoFilho(final Integer parm) {
        idGrupoVisaoFilho = parm;
    }

    public Integer getIdCamposObjetoNegocioFilho() {
        return idCamposObjetoNegocioFilho;
    }

    public void setIdCamposObjetoNegocioFilho(final Integer parm) {
        idCamposObjetoNegocioFilho = parm;
    }

    public Integer getIdCamposObjetoNegocioPaiNN() {
        return idCamposObjetoNegocioPaiNN;
    }

    public void setIdCamposObjetoNegocioPaiNN(final Integer parm) {
        idCamposObjetoNegocioPaiNN = parm;
    }

    public Integer getIdCamposObjetoNegocioFilhoNN() {
        return idCamposObjetoNegocioFilhoNN;
    }

    public void setIdCamposObjetoNegocioFilhoNN(final Integer parm) {
        idCamposObjetoNegocioFilhoNN = parm;
    }

    public String getControle() {
        return controle;
    }

    public void setControle(final String controle) {
        this.controle = controle;
    }

    public String getNomeDBPai() {
        return nomeDBPai;
    }

    public void setNomeDBPai(final String nomeDBPai) {
        this.nomeDBPai = nomeDBPai;
    }

    public String getNomeDBPaiFilhoNN() {
        return nomeDBPaiFilhoNN;
    }

    public void setNomeDBPaiFilhoNN(final String nomeDBPaiFilhoNN) {
        this.nomeDBPaiFilhoNN = nomeDBPaiFilhoNN;
    }

    public String getNomeDBPaiNN() {
        return nomeDBPaiNN;
    }

    public void setNomeDBPaiNN(final String nomeDBPaiNN) {
        this.nomeDBPaiNN = nomeDBPaiNN;
    }

    public String getNomeDBPaiFilho() {
        return nomeDBPaiFilho;
    }

    public void setNomeDBPaiFilho(final String nomeDBPaiFilho) {
        this.nomeDBPaiFilho = nomeDBPaiFilho;
    }

    public String getNomeTabelaFilho() {
        return nomeTabelaFilho;
    }

    public void setNomeTabelaFilho(final String nomeTabelaFilho) {
        this.nomeTabelaFilho = nomeTabelaFilho;
    }

    public String getNomeTabelaFilhoNN() {
        return nomeTabelaFilhoNN;
    }

    public void setNomeTabelaFilhoNN(final String nomeTabelaFilhoNN) {
        this.nomeTabelaFilhoNN = nomeTabelaFilhoNN;
    }

    public String getNomeTabelaPai() {
        return nomeTabelaPai;
    }

    public void setNomeTabelaPai(final String nomeTabelaPai) {
        this.nomeTabelaPai = nomeTabelaPai;
    }

    public String getNomeTabelaPaiNN() {
        return nomeTabelaPaiNN;
    }

    public void setNomeTabelaPaiNN(final String nomeTabelaPaiNN) {
        this.nomeTabelaPaiNN = nomeTabelaPaiNN;
    }

}
