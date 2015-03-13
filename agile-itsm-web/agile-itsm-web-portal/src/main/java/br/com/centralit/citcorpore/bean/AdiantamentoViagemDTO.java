package br.com.centralit.citcorpore.bean;

import java.sql.Timestamp;

import br.com.agileitsm.model.support.BaseEntity;

public class AdiantamentoViagemDTO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Integer idAdiantamentoViagem;
    private Integer idResponsavel;
    private Integer idSolicitacaoServico;
    private Integer idEmpregado;
    private Timestamp dataHora;
    private Double valorTotalAdiantado;
    private String situacao;
    private String observacoes;
    private String cancelarRequisicao;

    private String adiantamentoViagemSerialize;

    private Integer idContrato;

    private String integranteFuncionario;

    private String nomeNaoFuncionario;

    public Integer getIdAdiantamentoViagem() {
        return idAdiantamentoViagem;
    }

    public void setIdAdiantamentoViagem(final Integer idAdiantamentoViagem) {
        this.idAdiantamentoViagem = idAdiantamentoViagem;
    }

    public Integer getIdResponsavel() {
        return idResponsavel;
    }

    public void setIdResponsavel(final Integer idResponsavel) {
        this.idResponsavel = idResponsavel;
    }

    public Integer getIdSolicitacaoServico() {
        return idSolicitacaoServico;
    }

    public void setIdSolicitacaoServico(final Integer idSolicitacaoServico) {
        this.idSolicitacaoServico = idSolicitacaoServico;
    }

    public Integer getIdEmpregado() {
        return idEmpregado;
    }

    public void setIdEmpregado(final Integer idEmpregado) {
        this.idEmpregado = idEmpregado;
    }

    public Timestamp getDataHora() {
        return dataHora;
    }

    public void setDataHora(final Timestamp dataHora) {
        this.dataHora = dataHora;
    }

    public Double getValorTotalAdiantado() {
        return valorTotalAdiantado;
    }

    public void setValorTotalAdiantado(final Double valorTotalAdiantado) {
        this.valorTotalAdiantado = valorTotalAdiantado;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(final String situacao) {
        this.situacao = situacao;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(final String observacoes) {
        this.observacoes = observacoes;
    }

    public String getAdiantamentoViagemSerialize() {
        return adiantamentoViagemSerialize;
    }

    public void setAdiantamentoViagemSerialize(final String adiantamentoViagemSerialize) {
        this.adiantamentoViagemSerialize = adiantamentoViagemSerialize;
    }

    public Integer getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(final Integer idContrato) {
        this.idContrato = idContrato;
    }

    public String getIntegranteFuncionario() {
        return integranteFuncionario;
    }

    public void setIntegranteFuncionario(final String integranteFuncionario) {
        this.integranteFuncionario = integranteFuncionario;
    }

    public String getNomeNaoFuncionario() {
        return nomeNaoFuncionario;
    }

    public void setNomeNaoFuncionario(final String nomeNaoFuncionario) {
        this.nomeNaoFuncionario = nomeNaoFuncionario;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    /**
     * @return the cancelarRequisicao
     */
    public String getCancelarRequisicao() {
        return cancelarRequisicao;
    }

    /**
     * @param cancelarRequisicao
     *            the cancelarRequisicao to set
     */
    public void setCancelarRequisicao(final String cancelarRequisicao) {
        this.cancelarRequisicao = cancelarRequisicao;
    }

}
