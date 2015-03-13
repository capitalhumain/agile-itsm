package br.com.centralit.citcorpore.bean;

import java.sql.Date;

import br.com.agileitsm.model.support.BaseEntity;

public class RelatorioSolicitacaoPorExecutanteDTO extends BaseEntity {

    private Integer idSolicitacaoServico;

    private Integer idContrato;

    private Date dataInicio;

    private Date dataFim;

    private String formatoArquivoRelatorio;

    private Integer idResponsavelAtual;

    private String nomeServico;

    private String nomeResponsavelAtual;

    private String situacao;

    /**
     * Valor do top List
     *
     * @author thyen.chang
     */
    private Integer topList;

    public Integer getTopList() {
        return topList;
    }

    public void setTopList(final Integer topList) {
        this.topList = topList;
    }

    /**
     * @return the idContrato
     */
    public Integer getIdContrato() {
        return idContrato;
    }

    /**
     * @param idContrato
     *            the idContrato to set
     */
    public void setIdContrato(final Integer idContrato) {
        this.idContrato = idContrato;
    }

    /**
     * @return the dataInicio
     */
    public Date getDataInicio() {
        return dataInicio;
    }

    /**
     * @param dataInicio
     *            the dataInicio to set
     */
    public void setDataInicio(final Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    /**
     * @return the dataFim
     */
    public Date getDataFim() {
        return dataFim;
    }

    /**
     * @param dataFim
     *            the dataFim to set
     */
    public void setDataFim(final Date dataFim) {
        this.dataFim = dataFim;
    }

    /**
     * @return the formatoArquivoRelatorio
     */
    public String getFormatoArquivoRelatorio() {
        return formatoArquivoRelatorio;
    }

    /**
     * @param formatoArquivoRelatorio
     *            the formatoArquivoRelatorio to set
     */
    public void setFormatoArquivoRelatorio(final String formatoArquivoRelatorio) {
        this.formatoArquivoRelatorio = formatoArquivoRelatorio;
    }

    /**
     * @return the idSolicitacaoServico
     */
    public Integer getIdSolicitacaoServico() {
        return idSolicitacaoServico;
    }

    /**
     * @param idSolicitacaoServico
     *            the idSolicitacaoServico to set
     */
    public void setIdSolicitacaoServico(final Integer idSolicitacaoServico) {
        this.idSolicitacaoServico = idSolicitacaoServico;
    }

    /**
     * @return the idResponsavelAtual
     */
    public Integer getIdResponsavelAtual() {
        return idResponsavelAtual;
    }

    /**
     * @param idResponsavelAtual
     *            the idResponsavelAtual to set
     */
    public void setIdResponsavelAtual(final Integer idResponsavelAtual) {
        this.idResponsavelAtual = idResponsavelAtual;
    }

    /**
     * @return the nomeServico
     */
    public String getNomeServico() {
        return nomeServico;
    }

    /**
     * @param nomeServico
     *            the nomeServico to set
     */
    public void setNomeServico(final String nomeServico) {
        this.nomeServico = nomeServico;
    }

    /**
     * @return the nomeResponsavelAtual
     */
    public String getNomeResponsavelAtual() {
        return nomeResponsavelAtual;
    }

    /**
     * @param nomeResponsavelAtual
     *            the nomeResponsavelAtual to set
     */
    public void setNomeResponsavelAtual(final String nomeResponsavelAtual) {
        this.nomeResponsavelAtual = nomeResponsavelAtual;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(final String situacao) {
        this.situacao = situacao;
    }

}
