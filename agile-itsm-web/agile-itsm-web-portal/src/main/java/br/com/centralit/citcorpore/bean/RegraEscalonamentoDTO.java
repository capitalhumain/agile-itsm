package br.com.centralit.citcorpore.bean;

import java.sql.Date;
import java.util.Collection;

import br.com.agileitsm.model.support.BaseEntity;

/**
 * @author euler.ramos
 *
 */
public class RegraEscalonamentoDTO extends BaseEntity {

    private static final long serialVersionUID = -3721270786353311618L;

    private Integer idRegraEscalonamento;
    private Integer idTipoGerenciamento;
    private Integer idServico;
    private Integer idContrato;
    private Integer idSolicitante;
    private Integer idGrupo;
    private Integer idTipoDemandaServico;
    private String urgencia;
    private String impacto;
    private Integer tempoExecucao;
    private Integer intervaloNotificacao;
    private String enviarEmail;
    private String criaProblema;
    private Date dataInicio;
    private Date dataFim;
    private Integer prazoCriarProblema;
    private Integer tipoDataEscalonamento;

    private String grupo;
    private String nomeSolicitante;
    private String servico;

    private Collection<EscalonamentoDTO> colEscalonamentoDTOs;

    public Integer getIdRegraEscalonamento() {
        return idRegraEscalonamento;
    }

    public void setIdRegraEscalonamento(final Integer idRegraEscalonamento) {
        this.idRegraEscalonamento = idRegraEscalonamento;
    }

    public Integer getIdTipoGerenciamento() {
        return idTipoGerenciamento;
    }

    public void setIdTipoGerenciamento(final Integer idTipoGerenciamento) {
        this.idTipoGerenciamento = idTipoGerenciamento;
    }

    public Integer getIdServico() {
        return idServico;
    }

    public void setIdServico(final Integer idServico) {
        this.idServico = idServico;
    }

    public Integer getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(final Integer idContrato) {
        this.idContrato = idContrato;
    }

    public Integer getIdSolicitante() {
        return idSolicitante;
    }

    public void setIdSolicitante(final Integer idSolicitante) {
        this.idSolicitante = idSolicitante;
    }

    public Integer getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(final Integer idGrupo) {
        this.idGrupo = idGrupo;
    }

    public Integer getIdTipoDemandaServico() {
        return idTipoDemandaServico;
    }

    public void setIdTipoDemandaServico(final Integer idTipoDemandaServico) {
        this.idTipoDemandaServico = idTipoDemandaServico;
    }

    public String getUrgencia() {
        return urgencia;
    }

    public void setUrgencia(final String urgencia) {
        this.urgencia = urgencia;
    }

    public String getImpacto() {
        return impacto;
    }

    public void setImpacto(final String impacto) {
        this.impacto = impacto;
    }

    public Integer getTempoExecucao() {
        return tempoExecucao;
    }

    public void setTempoExecucao(final Integer tempoExecucao) {
        this.tempoExecucao = tempoExecucao;
    }

    public Integer getIntervaloNotificacao() {
        return intervaloNotificacao;
    }

    public void setIntervaloNotificacao(final Integer intervaloNotificacao) {
        this.intervaloNotificacao = intervaloNotificacao;
    }

    public String getEnviarEmail() {
        return enviarEmail;
    }

    public void setEnviarEmail(final String enviarEmail) {
        this.enviarEmail = enviarEmail;
    }

    public Collection<EscalonamentoDTO> getColEscalonamentoDTOs() {
        return colEscalonamentoDTOs;
    }

    public void setColEscalonamentoDTOs(final Collection<EscalonamentoDTO> colEscalonamentoDTOs) {
        this.colEscalonamentoDTOs = colEscalonamentoDTOs;
    }

    public String getCriaProblema() {
        return criaProblema;
    }

    public void setCriaProblema(final String criaProblema) {
        this.criaProblema = criaProblema;
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

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(final String grupo) {
        this.grupo = grupo;
    }

    public String getNomeSolicitante() {
        return nomeSolicitante;
    }

    public void setNomeSolicitante(final String nomeSolicitante) {
        this.nomeSolicitante = nomeSolicitante;
    }

    public String getServico() {
        return servico;
    }

    public void setServico(final String servico) {
        this.servico = servico;
    }

    public Integer getPrazoCriarProblema() {
        return prazoCriarProblema;
    }

    public void setPrazoCriarProblema(final Integer prazoCriarProblema) {
        this.prazoCriarProblema = prazoCriarProblema;
    }

    public Integer getTipoDataEscalonamento() {
        return tipoDataEscalonamento;
    }

    public void setTipoDataEscalonamento(final Integer tipoDataEscalonamento) {
        this.tipoDataEscalonamento = tipoDataEscalonamento;
    }

}
