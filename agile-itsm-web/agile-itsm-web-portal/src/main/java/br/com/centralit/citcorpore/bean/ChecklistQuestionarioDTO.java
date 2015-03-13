package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class ChecklistQuestionarioDTO extends BaseEntity {

    private Integer idChecklistQuestionario;
    private Integer idContrato;
    private Integer idQuestionario;
    private Integer idQuestionarioOrigem;
    private Integer idRequisicao;
    private Integer idTarefa;

    private Integer idTipoAba;
    private Integer idTipoRequisicao;

    private Integer idRequisicaoQuestionario;

    private String valorConfirmacao;

    public Integer getIdQuestionarioOrigem() {
        return idQuestionarioOrigem;
    }

    public void setIdQuestionarioOrigem(final Integer idQuestionarioOrigem) {
        this.idQuestionarioOrigem = idQuestionarioOrigem;
    }

    public Integer getIdTipoAba() {
        return idTipoAba;
    }

    public void setIdTipoAba(final Integer idTipoAba) {
        this.idTipoAba = idTipoAba;
    }

    public Integer getIdTipoRequisicao() {
        return idTipoRequisicao;
    }

    public void setIdTipoRequisicao(final Integer idTipoRequisicao) {
        this.idTipoRequisicao = idTipoRequisicao;
    }

    public Integer getIdQuestionario() {
        return idQuestionario;
    }

    public void setIdQuestionario(final Integer idQuestionario) {
        this.idQuestionario = idQuestionario;
    }

    private String tipoApresResumo;
    private String ultimoComando;

    public Integer getIdChecklistQuestionario() {
        return idChecklistQuestionario;
    }

    public void setIdChecklistQuestionario(final Integer idChecklistQuestionario) {
        this.idChecklistQuestionario = idChecklistQuestionario;
    }

    public Integer getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(final Integer idContrato) {
        this.idContrato = idContrato;
    }

    public String getTipoApresResumo() {
        return tipoApresResumo;
    }

    public void setTipoApresResumo(final String tipoApresResumo) {
        this.tipoApresResumo = tipoApresResumo;
    }

    public String getUltimoComando() {
        return ultimoComando;
    }

    public void setUltimoComando(final String ultimoComando) {
        this.ultimoComando = ultimoComando;
    }

    /**
     * @return the idRequisicao
     */
    public Integer getIdRequisicao() {
        return idRequisicao;
    }

    /**
     * @param idRequisicao
     *            the idRequisicao to set
     */
    public void setIdRequisicao(final Integer idRequisicao) {
        this.idRequisicao = idRequisicao;
    }

    public Integer getIdRequisicaoQuestionario() {
        return idRequisicaoQuestionario;
    }

    public void setIdRequisicaoQuestionario(final Integer idRequisicaoQuestionario) {
        this.idRequisicaoQuestionario = idRequisicaoQuestionario;
    }

    public String getValorConfirmacao() {
        return valorConfirmacao;
    }

    public void setValorConfirmacao(final String valorConfirmacao) {
        this.valorConfirmacao = valorConfirmacao;
    }

    /**
     * @return the idTarefa
     */
    public Integer getIdTarefa() {
        return idTarefa;
    }

    /**
     * @param idTarefa
     *            the idTarefa to set
     */
    public void setIdTarefa(final Integer idTarefa) {
        this.idTarefa = idTarefa;
    }

}
