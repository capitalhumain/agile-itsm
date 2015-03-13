package br.com.centralit.citcorpore.bean;

import java.sql.Timestamp;

import br.com.agileitsm.model.support.BaseEntity;

public class HistoricoSolicitacaoServicoDTO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Integer idHistoricoSolicitacao;
    private Integer idSolicitacaoServico;
    private Integer idResponsavelAtual;
    private Integer idGrupo;
    private Integer idOcorrencia;
    private Timestamp dataCriacao;
    private Timestamp dataFinal;
    private Double horasTrabalhadas;
    private Integer idServicoContrato;
    private Integer idCalendario;
    private String status;

    public Integer getIdHistoricoSolicitacao() {
        return idHistoricoSolicitacao;
    }

    public void setIdHistoricoSolicitacao(final Integer idHistoricoSolicitacao) {
        this.idHistoricoSolicitacao = idHistoricoSolicitacao;
    }

    public Integer getIdResponsavelAtual() {
        return idResponsavelAtual;
    }

    public void setIdResponsavelAtual(final Integer idResponsavelAtual) {
        this.idResponsavelAtual = idResponsavelAtual;
    }

    public Integer getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(final Integer idGrupo) {
        this.idGrupo = idGrupo;
    }

    public Integer getIdOcorrencia() {
        return idOcorrencia;
    }

    public void setIdOcorrencia(final Integer idOcorrencia) {
        this.idOcorrencia = idOcorrencia;
    }

    public Double getHorasTrabalhadas() {
        return horasTrabalhadas;
    }

    public void setHorasTrabalhadas(final Double horasTrabalhadas) {
        this.horasTrabalhadas = horasTrabalhadas;
    }

    public Integer getIdSolicitacaoServico() {
        return idSolicitacaoServico;
    }

    public void setIdSolicitacaoServico(final Integer idSolicitacaoServico) {
        this.idSolicitacaoServico = idSolicitacaoServico;
    }

    public Timestamp getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(final Timestamp timestamp) {
        dataCriacao = timestamp;
    }

    public Timestamp getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(final Timestamp dataFinal) {
        this.dataFinal = dataFinal;
    }

    public Integer getIdServicoContrato() {
        return idServicoContrato;
    }

    public void setIdServicoContrato(final Integer idServicoContrato) {
        this.idServicoContrato = idServicoContrato;
    }

    public Integer getIdCalendario() {
        return idCalendario;
    }

    public void setIdCalendario(final Integer idCalendario) {
        this.idCalendario = idCalendario;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(final String status) {
        this.status = status;
    }

}
