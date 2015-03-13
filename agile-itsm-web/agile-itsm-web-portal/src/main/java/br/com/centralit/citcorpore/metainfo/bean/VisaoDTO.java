package br.com.centralit.citcorpore.metainfo.bean;

import java.util.Collection;
import java.util.HashMap;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.MatrizVisaoDTO;

public class VisaoDTO extends BaseEntity {

    public static String EDIT = "ED";
    public static String TABLESEARCH = "TS";
    public static String TABLEEDIT = "TE";
    public static String EXTERNALCLASS = "EC";
    public static String MATRIZ = "MT";

    private Integer idVisao;
    private String descricao;
    private String tipoVisao;
    private String situacao;
    private String classeName;
    private String identificador;

    private Integer numero;
    private String nome;
    private String tipoNegocio;

    private boolean filha;
    private String acaoVisaoFilhaPesqRelacionada;

    private Collection colGrupos;
    private Collection colVisoesRelacionadas;
    private Collection colScripts;
    private Collection colBotoes;
    private Collection colHtmlCode;

    private HashMap mapScripts;
    private HashMap mapHtmlCodes;

    private MatrizVisaoDTO matrizVisaoDTO;

    private Integer idObjetoNegocioMatriz;
    private Integer idCamposObjetoNegocio1;
    private Integer idCamposObjetoNegocio2;
    private Integer idCamposObjetoNegocio3;
    private String descricaoCampo1;
    private String descricaoCampo2;
    private String descricaoCampo3;

    public Integer getIdVisao() {
        return idVisao;
    }

    public void setIdVisao(final Integer parm) {
        idVisao = parm;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(final String parm) {
        descricao = parm;
    }

    public String getTipoVisao() {
        return tipoVisao;
    }

    public void setTipoVisao(final String parm) {
        tipoVisao = parm;
    }

    public String getSituacao() {
        return situacao;
    }

    public String getSituacaoVisao() {
        return situacao;
    }

    public void setSituacao(final String parm) {
        situacao = parm;
    }

    public Collection getColGrupos() {
        return colGrupos;
    }

    public void setColGrupos(final Collection colGrupos) {
        this.colGrupos = colGrupos;
    }

    public Collection getColVisoesRelacionadas() {
        return colVisoesRelacionadas;
    }

    public void setColVisoesRelacionadas(final Collection colVisoesRelacionadas) {
        this.colVisoesRelacionadas = colVisoesRelacionadas;
    }

    public boolean getFilha() {
        return filha;
    }

    public void setFilha(final boolean filha) {
        this.filha = filha;
    }

    public String getAcaoVisaoFilhaPesqRelacionada() {
        return acaoVisaoFilhaPesqRelacionada;
    }

    public void setAcaoVisaoFilhaPesqRelacionada(final String acaoVisaoFilhaPesqRelacionada) {
        this.acaoVisaoFilhaPesqRelacionada = acaoVisaoFilhaPesqRelacionada;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(final Integer numero) {
        this.numero = numero;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(final String nome) {
        this.nome = nome;
    }

    public String getTipoNegocio() {
        return tipoNegocio;
    }

    public void setTipoNegocio(final String tipoNegocio) {
        this.tipoNegocio = tipoNegocio;
    }

    public Collection getColScripts() {
        return colScripts;
    }

    public void setColScripts(final Collection colScripts) {
        this.colScripts = colScripts;
    }

    public HashMap getMapScripts() {
        return mapScripts;
    }

    public void setMapScripts(final HashMap mapScripts) {
        this.mapScripts = mapScripts;
    }

    public String getClasseName() {
        return classeName;
    }

    public void setClasseName(final String classeName) {
        this.classeName = classeName;
    }

    public Collection getColBotoes() {
        return colBotoes;
    }

    public void setColBotoes(final Collection colBotoes) {
        this.colBotoes = colBotoes;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(final String identificador) {
        this.identificador = identificador;
    }

    public Collection getColHtmlCode() {
        return colHtmlCode;
    }

    public void setColHtmlCode(final Collection colHtmlCode) {
        this.colHtmlCode = colHtmlCode;
    }

    public HashMap getMapHtmlCodes() {
        return mapHtmlCodes;
    }

    public void setMapHtmlCodes(final HashMap mapHtmlCodes) {
        this.mapHtmlCodes = mapHtmlCodes;
    }

    public MatrizVisaoDTO getMatrizVisaoDTO() {
        return matrizVisaoDTO;
    }

    public void setMatrizVisaoDTO(final MatrizVisaoDTO matrizVisaoDTO) {
        this.matrizVisaoDTO = matrizVisaoDTO;
    }

    public Integer getIdObjetoNegocioMatriz() {
        return idObjetoNegocioMatriz;
    }

    public void setIdObjetoNegocioMatriz(final Integer idObjetoNegocioMatriz) {
        this.idObjetoNegocioMatriz = idObjetoNegocioMatriz;
    }

    public Integer getIdCamposObjetoNegocio1() {
        return idCamposObjetoNegocio1;
    }

    public void setIdCamposObjetoNegocio1(final Integer idCamposObjetoNegocio1) {
        this.idCamposObjetoNegocio1 = idCamposObjetoNegocio1;
    }

    public Integer getIdCamposObjetoNegocio2() {
        return idCamposObjetoNegocio2;
    }

    public void setIdCamposObjetoNegocio2(final Integer idCamposObjetoNegocio2) {
        this.idCamposObjetoNegocio2 = idCamposObjetoNegocio2;
    }

    public Integer getIdCamposObjetoNegocio3() {
        return idCamposObjetoNegocio3;
    }

    public void setIdCamposObjetoNegocio3(final Integer idCamposObjetoNegocio3) {
        this.idCamposObjetoNegocio3 = idCamposObjetoNegocio3;
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
}
