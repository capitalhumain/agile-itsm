package br.com.centralit.citcorpore.bean;

import java.sql.Date;

import org.jsoup.Jsoup;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.util.Util;

public class RelatorioSolicitacaoReabertaDTO extends BaseEntity {

    private static final long serialVersionUID = -53946557530130345L;

    private Date dataInicialReabertura;
    private Date dataFinalReabertura;
    private Date dataInicialEncerramento;
    private Date dataFinalEncerramento;
    private Integer idContrato;
    private Integer idGrupo;
    private String situacao;
    private Integer idTipoDemandaServico;
    private Integer idSolicitacaoServico;
    private Date dataInicio;
    private Date dataFim;
    private String formato;
    private Date dataReabertura;
    private String horaReabertura;
    private Date dataHoraCriacao;
    private String origem;
    private String nomeServico;
    private Date dataHoraInicioAtendimento;
    private Date dataHoraFimAtendimento;
    private String solicitante;
    private String nomeUnidade;
    private String descricaoServico;
    private String nomeTipoDemandaServico;
    private String nomeResponsavel;
    private Integer quantidade;
    private String dataHoraReabertura;
    private String grupo;
    /**
     * Valor TOP List
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

    public Integer getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(final Integer idContrato) {
        this.idContrato = idContrato;
    }

    public Integer getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(final Integer idGrupo) {
        this.idGrupo = idGrupo;
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

    /**
     * @return the dataInicialReabertura
     */
    public Date getDataInicialReabertura() {
        return dataInicialReabertura;
    }

    /**
     * @param dataInicialReabertura
     *            the dataInicialReabertura to set
     */
    public void setDataInicialReabertura(final Date dataInicialReabertura) {
        this.dataInicialReabertura = dataInicialReabertura;
    }

    /**
     * @return the dataFinalReabertura
     */
    public Date getDataFinalReabertura() {
        return dataFinalReabertura;
    }

    /**
     * @param dataFinalReabertura
     *            the dataFinalReabertura to set
     */
    public void setDataFinalReabertura(final Date dataFinalReabertura) {
        this.dataFinalReabertura = dataFinalReabertura;
    }

    /**
     * @return the dataInicialEncerramento
     */
    public Date getDataInicialEncerramento() {
        return dataInicialEncerramento;
    }

    /**
     * @param dataInicialEncerramento
     *            the dataInicialEncerramento to set
     */
    public void setDataInicialEncerramento(final Date dataInicialEncerramento) {
        this.dataInicialEncerramento = dataInicialEncerramento;
    }

    /**
     * @return the dataFinalEncerramento
     */
    public Date getDataFinalEncerramento() {
        return dataFinalEncerramento;
    }

    /**
     * @param dataFinalEncerramento
     *            the dataFinalEncerramento to set
     */
    public void setDataFinalEncerramento(final Date dataFinalEncerramento) {
        this.dataFinalEncerramento = dataFinalEncerramento;
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
     * @return the formato
     */
    public String getFormato() {
        return formato;
    }

    /**
     * @param formato
     *            the formato to set
     */
    public void setFormato(final String formato) {
        this.formato = formato;
    }

    /**
     * @return the dataReabertura
     */
    public Date getDataReabertura() {
        return dataReabertura;
    }

    /**
     * @param dataReabertura
     *            the dataReabertura to set
     */
    public void setDataReabertura(final Date dataReabertura) {
        this.dataReabertura = dataReabertura;
    }

    /**
     * @return the horaReabertura
     */
    public String getHoraReabertura() {
        return horaReabertura;
    }

    /**
     * @param horaReabertura
     *            the horaReabertura to set
     */
    public void setHoraReabertura(final String horaReabertura) {
        this.horaReabertura = horaReabertura;
    }

    /**
     * @return the dataHoraCriacao
     */
    public Date getDataHoraCriacao() {
        return dataHoraCriacao;
    }

    /**
     * @param dataHoraCriacao
     *            the dataHoraCriacao to set
     */
    public void setDataHoraCriacao(final Date dataHoraCriacao) {
        this.dataHoraCriacao = dataHoraCriacao;
    }

    /**
     * @return the origem
     */
    public String getOrigem() {
        return origem;
    }

    /**
     * @param origem
     *            the origem to set
     */
    public void setOrigem(final String origem) {
        this.origem = origem;
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
     * @return the dataHoraInicioAtendimento
     */
    public Date getDataHoraInicioAtendimento() {
        return dataHoraInicioAtendimento;
    }

    /**
     * @param dataHoraInicioAtendimento
     *            the dataHoraInicioAtendimento to set
     */
    public void setDataHoraInicioAtendimento(final Date dataHoraInicioAtendimento) {
        this.dataHoraInicioAtendimento = dataHoraInicioAtendimento;
    }

    /**
     * @return the dataHoraFimAtendimento
     */
    public Date getDataHoraFimAtendimento() {
        return dataHoraFimAtendimento;
    }

    /**
     * @param dataHoraFimAtendimento
     *            the dataHoraFimAtendimento to set
     */
    public void setDataHoraFimAtendimento(final Date dataHoraFimAtendimento) {
        this.dataHoraFimAtendimento = dataHoraFimAtendimento;
    }

    /**
     * @return the solicitante
     */
    public String getSolicitante() {
        return solicitante;
    }

    /**
     * @param solicitante
     *            the solicitante to set
     */
    public void setSolicitante(final String solicitante) {
        this.solicitante = solicitante;
    }

    /**
     * @return the nomeUnidade
     */
    public String getNomeUnidade() {
        return nomeUnidade;
    }

    /**
     * @param nomeUnidade
     *            the nomeUnidade to set
     */
    public void setNomeUnidade(final String nomeUnidade) {
        this.nomeUnidade = nomeUnidade;
    }

    /**
     * @return the descricaoServico
     */
    public String getDescricaoServico() {
        return descricaoServico;
    }

    /**
     * @return the descricaoServicoSemFormatacao
     */
    public String getDescricaoServicoSemFormatacao() {
        if (descricaoServico != null) {
            descricaoServico = Jsoup.parse(descricaoServico).text();
        } else {
            descricaoServico = "";
        }
        return descricaoServico;
    }

    /**
     * @param descricaoServico
     *            the descricaoServico to set
     */
    public void setDescricaoServico(final String descricaoServico) {
        this.descricaoServico = descricaoServico;
    }

    /**
     * @return the nomeTipoDemandaServico
     */
    public String getNomeTipoDemandaServico() {
        return nomeTipoDemandaServico;
    }

    /**
     * @param nomeTipoDemandaServico
     *            the nomeTipoDemandaServico to set
     */
    public void setNomeTipoDemandaServico(final String nomeTipoDemandaServico) {
        this.nomeTipoDemandaServico = nomeTipoDemandaServico;
    }

    /**
     * @return the nomeResponsavel
     */
    public String getNomeResponsavel() {
        return nomeResponsavel;
    }

    /**
     * @param nomeResponsavel
     *            the nomeResponsavel to set
     */
    public void setNomeResponsavel(final String nomeResponsavel) {
        this.nomeResponsavel = nomeResponsavel;
    }

    /**
     * @return the quantidade
     */
    public Integer getQuantidade() {
        return quantidade;
    }

    /**
     * @param quantidade
     *            the quantidade to set
     */
    public void setQuantidade(final Integer quantidade) {
        this.quantidade = quantidade;
    }

    /**
     * @return the dataHoraReabertura
     */
    public String getDataHoraReabertura() {
        dataHoraReabertura = Util.converteDataUtilToString(dataReabertura) + " " + horaReabertura;
        return dataHoraReabertura;
    }

    /**
     * @return the grupo
     */
    public String getGrupo() {
        return grupo;
    }

    /**
     * @param grupo
     *            the grupo to set
     */
    public void setGrupo(final String grupo) {
        this.grupo = grupo;
    }

}
