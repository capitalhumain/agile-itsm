package br.com.centralit.citcorpore.bean;

import java.sql.Date;

import br.com.agileitsm.model.support.BaseEntity;

public class RelatorioTop10IncidentesRequisicoesDTO extends BaseEntity {

    private static final long serialVersionUID = -53946557530130345L;

    private Integer idRelatorio;
    private Date dataInicial;
    private Date dataFinal;
    private Integer idContrato;
    private Integer idPrioridade;
    private Integer idUnidade;
    private Integer idServico;
    private Integer idSolicitante;
    private String situacao;
    private Integer idTipoDemandaServico;
    private Integer idOrigem;
    private String visualizacao;
    private String formato;
    private Integer topList;

    public Integer getIdRelatorio() {
        return idRelatorio;
    }

    public void setIdRelatorio(final Integer idRelatorio) {
        this.idRelatorio = idRelatorio;
    }

    public Date getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(final Date dataInicial) {
        this.dataInicial = dataInicial;
    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(final Date dataFinal) {
        this.dataFinal = dataFinal;
    }

    public Integer getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(final Integer idContrato) {
        this.idContrato = idContrato;
    }

    public Integer getIdPrioridade() {
        return idPrioridade;
    }

    public void setIdPrioridade(final Integer idPrioridade) {
        this.idPrioridade = idPrioridade;
    }

    public Integer getIdUnidade() {
        return idUnidade;
    }

    public void setIdUnidade(final Integer idUnidade) {
        this.idUnidade = idUnidade;
    }

    public Integer getIdServico() {
        return idServico;
    }

    public void setIdServico(final Integer idServico) {
        this.idServico = idServico;
    }

    public Integer getIdSolicitante() {
        return idSolicitante;
    }

    public void setIdSolicitante(final Integer idSolicitante) {
        this.idSolicitante = idSolicitante;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(final String situacao) {
        this.situacao = situacao;
    }

    public Integer getIdTipoDemandaServico() {
        return idTipoDemandaServico;
    }

    public void setIdTipoDemandaServico(final Integer idTipoDemandaServico) {
        this.idTipoDemandaServico = idTipoDemandaServico;
    }

    public Integer getIdOrigem() {
        return idOrigem;
    }

    public void setIdOrigem(final Integer idOrigem) {
        this.idOrigem = idOrigem;
    }

    public String getVisualizacao() {
        return visualizacao;
    }

    public void setVisualizacao(final String visualizacao) {
        this.visualizacao = visualizacao;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(final String formato) {
        this.formato = formato;
    }

    public Integer getTopList() {
        return topList;
    }

    public void setTopList(final Integer topList) {
        this.topList = topList;
    }

}
