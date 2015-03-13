package br.com.centralit.citcorpore.bean;

import java.sql.Timestamp;

import br.com.agileitsm.model.support.BaseEntity;

public class AprovacaoRequisicaoLiberacaoDTO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Integer idAprovacaoRequisicaoLiberacao;
    private Integer idRequisicaoLiberacao;
    private Integer idResponsavel;
    private Integer idTarefa;
    private Integer idJustificativa;
    private Timestamp dataHora;
    private String complementoJustificativa;
    private String observacoes;
    private String aprovacao;

    public Integer getIdAprovacaoRequisicaoLiberacao() {
        return idAprovacaoRequisicaoLiberacao;
    }

    public void setIdAprovacaoRequisicaoLiberacao(final Integer idAprovacaoRequisicaoLiberacao) {
        this.idAprovacaoRequisicaoLiberacao = idAprovacaoRequisicaoLiberacao;
    }

    public Integer getIdRequisicaoLiberacao() {
        return idRequisicaoLiberacao;
    }

    public void setIdRequisicaoLiberacao(final Integer idRequisicaoLiberacao) {
        this.idRequisicaoLiberacao = idRequisicaoLiberacao;
    }

    public Integer getIdResponsavel() {
        return idResponsavel;
    }

    public void setIdResponsavel(final Integer idResponsavel) {
        this.idResponsavel = idResponsavel;
    }

    public Integer getIdTarefa() {
        return idTarefa;
    }

    public void setIdTarefa(final Integer idTarefa) {
        this.idTarefa = idTarefa;
    }

    public Integer getIdJustificativa() {
        return idJustificativa;
    }

    public void setIdJustificativa(final Integer idJustificativa) {
        this.idJustificativa = idJustificativa;
    }

    public Timestamp getDataHora() {
        return dataHora;
    }

    public void setDataHora(final Timestamp dataHora) {
        this.dataHora = dataHora;
    }

    public String getComplementoJustificativa() {
        return complementoJustificativa;
    }

    public void setComplementoJustificativa(final String complementoJustificativa) {
        this.complementoJustificativa = complementoJustificativa;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(final String observacoes) {
        this.observacoes = observacoes;
    }

    public String getAprovacao() {
        return aprovacao;
    }

    public void setAprovacao(final String aprovacao) {
        this.aprovacao = aprovacao;
    }

}
