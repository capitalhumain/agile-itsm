package br.com.centralit.citcorpore.bean;

import java.sql.Date;

import br.com.agileitsm.model.support.BaseEntity;

public class RelatorioIncidentesNaoResolvidosDTO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Date dataReferencia;
    private Date periodoReferencia;
    private Integer qtdDiasAbertos;
    private Integer idContrato;
    private String contrato;
    private String funcionario;
    private String listaGrupos;
    private String listaServicos;
    private String formatoArquivoRelatorio;
    private Integer numeroSolicitacao;
    private String nomeservico;
    private String responsavel;
    private String solicitante;
    private String tipoServico;
    private String situacao;
    private String dataCriacao;
    private Integer qtdDiasAtrasos;

    public String getListaServicos() {
        return listaServicos;
    }

    public void setListaServicos(final String listaServicos) {
        this.listaServicos = listaServicos;
    }

    public Integer getNumeroSolicitacao() {
        return numeroSolicitacao;
    }

    public void setNumeroSolicitacao(final Integer numeroSolicitacao) {
        this.numeroSolicitacao = numeroSolicitacao;
    }

    public String getNomeservico() {
        return nomeservico;
    }

    public void setNomeservico(final String nomeservico) {
        this.nomeservico = nomeservico;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(final String responsavel) {
        this.responsavel = responsavel;
    }

    public String getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(final String solicitante) {
        this.solicitante = solicitante;
    }

    public String getTipoServico() {
        return tipoServico;
    }

    public void setTipoServico(final String tipoServico) {
        this.tipoServico = tipoServico;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(final String situacao) {
        this.situacao = situacao;
    }

    public String getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(final String dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Integer getQtdDiasAtrasos() {
        return qtdDiasAtrasos;
    }

    public void setQtdDiasAtrasos(final Integer qtdDiasAtrasos) {
        this.qtdDiasAtrasos = qtdDiasAtrasos;
    }

    public Date getDataReferencia() {
        return dataReferencia;
    }

    public void setDataReferencia(final Date dataReferencia) {
        this.dataReferencia = dataReferencia;
    }

    public Date getPeriodoReferencia() {
        return periodoReferencia;
    }

    public void setPeriodoReferencia(final Date periodoReferencia) {
        this.periodoReferencia = periodoReferencia;
    }

    public Integer getQtdDiasAbertos() {
        return qtdDiasAbertos;
    }

    public void setQtdDiasAbertos(final Integer qtdDiasAbertos) {
        this.qtdDiasAbertos = qtdDiasAbertos;
    }

    public Integer getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(final Integer idContrato) {
        this.idContrato = idContrato;
    }

    public String getContrato() {
        return contrato;
    }

    public void setContrato(final String contrato) {
        this.contrato = contrato;
    }

    public String getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(final String funcionario) {
        this.funcionario = funcionario;
    }

    public String getListaGrupos() {
        return listaGrupos;
    }

    public void setListaGrupos(final String listaGrupos) {
        this.listaGrupos = listaGrupos;
    }

    public String getFormatoArquivoRelatorio() {
        return formatoArquivoRelatorio;
    }

    public void setFormatoArquivoRelatorio(final String formatoArquivoRelatorio) {
        this.formatoArquivoRelatorio = formatoArquivoRelatorio;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}
