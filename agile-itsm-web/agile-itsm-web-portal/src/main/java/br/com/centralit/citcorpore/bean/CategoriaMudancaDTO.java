package br.com.centralit.citcorpore.bean;

import java.sql.Date;

import br.com.agileitsm.model.support.BaseEntity;

public class CategoriaMudancaDTO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Integer idCategoriaMudanca;
    private Integer idCategoriaMudancaPai;
    private String nomeCategoria;
    private Date dataInicio;
    private Date dataFim;
    private Integer idTipoFluxo;
    private Integer idModeloEmailCriacao;
    private Integer idModeloEmailFinalizacao;
    private Integer idModeloEmailAcoes;
    private Integer idGrupoNivel1;
    private Integer idGrupoExecutor;
    private Integer idCalendario;

    public Integer getIdModeloEmailCriacao() {
        return idModeloEmailCriacao;
    }

    public void setIdModeloEmailCriacao(final Integer idModeloEmailCriacao) {
        this.idModeloEmailCriacao = idModeloEmailCriacao;
    }

    public Integer getIdModeloEmailFinalizacao() {
        return idModeloEmailFinalizacao;
    }

    public void setIdModeloEmailFinalizacao(final Integer idModeloEmailFinalizacao) {
        this.idModeloEmailFinalizacao = idModeloEmailFinalizacao;
    }

    public Integer getIdModeloEmailAcoes() {
        return idModeloEmailAcoes;
    }

    public void setIdModeloEmailAcoes(final Integer idModeloEmailAcoes) {
        this.idModeloEmailAcoes = idModeloEmailAcoes;
    }

    public Integer getIdGrupoNivel1() {
        return idGrupoNivel1;
    }

    public void setIdGrupoNivel1(final Integer idGrupoNivel1) {
        this.idGrupoNivel1 = idGrupoNivel1;
    }

    public Integer getIdGrupoExecutor() {
        return idGrupoExecutor;
    }

    public void setIdGrupoExecutor(final Integer idGrupoExecutor) {
        this.idGrupoExecutor = idGrupoExecutor;
    }

    public Integer getIdCalendario() {
        return idCalendario;
    }

    public void setIdCalendario(final Integer idCalendario) {
        this.idCalendario = idCalendario;
    }

    private int nivel;

    public String getNomeNivel() {
        if (this.getNomeCategoria() == null) {
            return nomeCategoria;
        }
        String str = "";
        for (int i = 0; i < this.getNivel(); i++) {
            str += "....";
        }
        return str + nomeCategoria;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(final int nivel) {
        this.nivel = nivel;
    }

    public Integer getIdCategoriaMudanca() {
        return idCategoriaMudanca;
    }

    public void setIdCategoriaMudanca(final Integer parm) {
        idCategoriaMudanca = parm;
    }

    public Integer getIdCategoriaMudancaPai() {
        return idCategoriaMudancaPai;
    }

    public void setIdCategoriaMudancaPai(final Integer parm) {
        idCategoriaMudancaPai = parm;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public void setNomeCategoria(final String parm) {
        nomeCategoria = parm;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(final Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(final Date dataFim) {
        this.dataFim = dataFim;
    }

    public Integer getIdTipoFluxo() {
        return idTipoFluxo;
    }

    public void setIdTipoFluxo(final Integer idTipoFluxo) {
        this.idTipoFluxo = idTipoFluxo;
    }

}
