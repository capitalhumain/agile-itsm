package br.com.centralit.citcorpore.bean;

import java.sql.Timestamp;

public class HistoricoMudancaDTO extends RequisicaoMudancaDTO {

    private static final long serialVersionUID = 1L;

    private Integer idHistoricoMudanca;
    private Timestamp dataHoraModificacao;
    private Integer idExecutorModificacao;
    private String tipoModificacao;
    private Double historicoVersao;
    private String nomeProprietario;
    private String baseLine;
    private String nomeExecutorModificacao;
    private String acaoFluxo;
    private String alterarSituacao;
    private String emailSolicitante;

    public Integer getIdHistoricoMudanca() {
        return idHistoricoMudanca;
    }

    public void setIdHistoricoMudanca(final Integer idHistoricoMudanca) {
        this.idHistoricoMudanca = idHistoricoMudanca;
    }

    public Timestamp getDataHoraModificacao() {
        return dataHoraModificacao;
    }

    @Override
    public String getAcaoFluxo() {
        return acaoFluxo;
    }

    @Override
    public void setAcaoFluxo(final String acaoFluxo) {
        this.acaoFluxo = acaoFluxo;
    }

    public void setDataHoraModificacao(final Timestamp dataHoraModificacao) {
        this.dataHoraModificacao = dataHoraModificacao;
    }

    public Integer getIdExecutorModificacao() {
        return idExecutorModificacao;
    }

    public void setIdExecutorModificacao(final Integer idExecutorModificacao) {
        this.idExecutorModificacao = idExecutorModificacao;
    }

    public String getTipoModificacao() {
        return tipoModificacao;
    }

    public void setTipoModificacao(final String tipoModificacao) {
        this.tipoModificacao = tipoModificacao;
    }

    public Double getHistoricoVersao() {
        return historicoVersao;
    }

    public void setHistoricoVersao(final Double historicoVersao) {
        this.historicoVersao = historicoVersao;
    }

    @Override
    public String getNomeProprietario() {
        return nomeProprietario;
    }

    @Override
    public void setNomeProprietario(final String nomeProprietario) {
        this.nomeProprietario = nomeProprietario;
    }

    public String getBaseLine() {
        return baseLine;
    }

    public void setBaseLine(final String baseLine) {
        this.baseLine = baseLine;
    }

    public String getNomeExecutorModificacao() {
        return nomeExecutorModificacao;
    }

    public void setNomeExecutorModificacao(final String nomeExecutorModificacao) {
        this.nomeExecutorModificacao = nomeExecutorModificacao;
    }

    @Override
    public String getAlterarSituacao() {
        return alterarSituacao;
    }

    @Override
    public void setAlterarSituacao(final String alterarSituacao) {
        this.alterarSituacao = alterarSituacao;
    }

    @Override
    public String getEmailSolicitante() {
        return emailSolicitante;
    }

    @Override
    public void setEmailSolicitante(final String emailSolicitante) {
        this.emailSolicitante = emailSolicitante;
    }

}
