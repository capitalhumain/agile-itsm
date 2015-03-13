package br.com.centralit.citcorpore.bean;

import java.sql.Date;

import br.com.agileitsm.model.support.BaseEntity;

public class TipoMudancaDTO extends BaseEntity {

    private static final long serialVersionUID = 4864126394598758208L;
    private Integer idTipoMudanca;
    private String nomeTipoMudanca;
    private Date dataInicio;
    private Date dataFim;
    private Integer idTipoFluxo;
    private Integer idModeloEmailCriacao;
    private Integer idModeloEmailFinalizacao;
    private Integer idModeloEmailAcoes;
    private Integer idGrupoExecutor;
    private Integer idCalendario;
    private String exigeAprovacao;

    private String impacto;

    private String urgencia;

    public Integer getIdTipoMudanca() {
        return idTipoMudanca;
    }

    public void setIdTipoMudanca(final Integer idTipoMudanca) {
        this.idTipoMudanca = idTipoMudanca;
    }

    public String getNomeTipoMudanca() {
        return nomeTipoMudanca;
    }

    public void setNomeTipoMudanca(final String nomeTipoMudanca) {
        this.nomeTipoMudanca = nomeTipoMudanca;
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

    /**
     * @return the impacto
     */
    public String getImpacto() {
        return impacto;
    }

    /**
     * @param impacto
     *            the impacto to set
     */
    public void setImpacto(final String impacto) {
        this.impacto = impacto;
    }

    /**
     * @return the urgencia
     */
    public String getUrgencia() {
        return urgencia;
    }

    /**
     * @param urgencia
     *            the urgencia to set
     */
    public void setUrgencia(final String urgencia) {
        this.urgencia = urgencia;
    }

    public String getExigeAprovacao() {
        return exigeAprovacao;
    }

    public void setExigeAprovacao(final String exigeAprovacao) {
        this.exigeAprovacao = exigeAprovacao;
    }

}
