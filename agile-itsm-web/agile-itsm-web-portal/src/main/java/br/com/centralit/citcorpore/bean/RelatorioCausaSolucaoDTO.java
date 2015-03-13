package br.com.centralit.citcorpore.bean;

import java.sql.Date;

import br.com.agileitsm.model.support.BaseEntity;

public class RelatorioCausaSolucaoDTO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Date dataInicio;
    private Date dataFim;
    private Integer idContrato;
    private Integer idTipoDemandaServico;
    private String situacao;
    private Integer[] idServicos;
    private Integer[] idGrupos;
    private Integer[] idCausas;
    private Integer[] idSolucoes;
    private Integer idSolicitacaoServico;
    private String descricaoCausa;
    private String descricaoCategoriaSolucao;
    private String status;
    private String nomeServico;
    private Integer numeroSolicitacoes;
    private String generationType;
    private String exibeSemCausa;
    private String exibeSemSolucao;

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

    public Integer getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(final Integer idContrato) {
        this.idContrato = idContrato;
    }

    public Integer getIdTipoDemandaServico() {
        return idTipoDemandaServico;
    }

    public void setIdTipoDemandaServico(final Integer idTipoDemandaServico) {
        this.idTipoDemandaServico = idTipoDemandaServico;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(final String situacao) {
        this.situacao = situacao;
    }

    public Integer[] getIdServicos() {
        return idServicos;
    }

    public void setIdServicos(final Integer[] idServicos) {
        this.idServicos = idServicos;
    }

    public Integer[] getIdGrupos() {
        return idGrupos;
    }

    public void setIdGrupos(final Integer[] idGrupos) {
        this.idGrupos = idGrupos;
    }

    public Integer[] getIdCausas() {
        return idCausas;
    }

    public void setIdCausas(final Integer[] idCausas) {
        this.idCausas = idCausas;
    }

    public Integer[] getIdSolucoes() {
        return idSolucoes;
    }

    public void setIdSolucoes(final Integer[] idSolucoes) {
        this.idSolucoes = idSolucoes;
    }

    public Integer getIdSolicitacaoServico() {
        return idSolicitacaoServico;
    }

    public void setIdSolicitacaoServico(final Integer idSolicitacaoServico) {
        this.idSolicitacaoServico = idSolicitacaoServico;
    }

    public String getDescricaoCausa() {
        return descricaoCausa;
    }

    public void setDescricaoCausa(final String descricaoCausa) {
        this.descricaoCausa = descricaoCausa;
    }

    public String getDescricaoCategoriaSolucao() {
        return descricaoCategoriaSolucao;
    }

    public void setDescricaoCategoriaSolucao(final String descricaoCategoriaSolucao) {
        this.descricaoCategoriaSolucao = descricaoCategoriaSolucao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(final String status) {
        this.status = status;
    }

    public String getNomeServico() {
        return nomeServico;
    }

    public void setNomeServico(final String nomeServico) {
        this.nomeServico = nomeServico;
    }

    public Integer getNumeroSolicitacoes() {
        return numeroSolicitacoes;
    }

    public void setNumeroSolicitacoes(final Integer numeroSolicitacoes) {
        this.numeroSolicitacoes = numeroSolicitacoes;
    }

    public String getGenerationType() {
        return generationType;
    }

    public void setGenerationType(final String generationType) {
        this.generationType = generationType;
    }

    public String getExibeSemCausa() {
        return exibeSemCausa;
    }

    public void setExibeSemCausa(final String exibeSemCausa) {
        this.exibeSemCausa = exibeSemCausa;
    }

    public String getExibeSemSolucao() {
        return exibeSemSolucao;
    }

    public void setExibeSemSolucao(final String exibeSemSolucao) {
        this.exibeSemSolucao = exibeSemSolucao;
    }

}
