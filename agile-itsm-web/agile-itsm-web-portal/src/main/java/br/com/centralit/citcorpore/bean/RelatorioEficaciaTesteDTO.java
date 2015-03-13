package br.com.centralit.citcorpore.bean;

//Criado por Bruno.Aquino

import java.sql.Date;

import br.com.agileitsm.model.support.BaseEntity;

public class RelatorioEficaciaTesteDTO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Date dataInicio;
    private Date dataFim;
    private String formatoArquivoRelatorio;
    private Integer idContrato;
    private String contrato;
    private String listaServicos;
    private String NomeServico;
    private Integer numeroSolicitacao;
    private String solicitante;
    private Date aberturaSolicitacao;
    private String situacao;

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(final String situacao) {
        this.situacao = situacao;
    }

    public Date getAberturaSolicitacao() {
        return aberturaSolicitacao;
    }

    public void setAberturaSolicitacao(final Date aberturaSolicitacao) {
        this.aberturaSolicitacao = aberturaSolicitacao;
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

    public String getFormatoArquivoRelatorio() {
        return formatoArquivoRelatorio;
    }

    public void setFormatoArquivoRelatorio(final String formatoArquivoRelatorio) {
        this.formatoArquivoRelatorio = formatoArquivoRelatorio;
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

    public String getListaServicos() {
        return listaServicos;
    }

    public void setListaServicos(final String listaServicos) {
        this.listaServicos = listaServicos;
    }

    public String getNomeServico() {
        return NomeServico;
    }

    public void setNomeServico(final String nomeServico) {
        NomeServico = nomeServico;
    }

    public Integer getNumeroSolicitacao() {
        return numeroSolicitacao;
    }

    public void setNumeroSolicitacao(final Integer numeroSolicitacao) {
        this.numeroSolicitacao = numeroSolicitacao;
    }

    public String getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(final String solicitante) {
        this.solicitante = solicitante;
    }

}
