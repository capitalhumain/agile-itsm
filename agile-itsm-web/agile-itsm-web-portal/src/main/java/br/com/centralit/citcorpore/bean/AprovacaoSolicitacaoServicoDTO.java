package br.com.centralit.citcorpore.bean;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import br.com.agileitsm.model.support.BaseEntity;

public class AprovacaoSolicitacaoServicoDTO extends BaseEntity {

    private Integer idAprovacaoSolicitacaoServico;
    private Integer idSolicitacaoServico;
    private Integer idResponsavel;
    private Integer idTarefa;
    private Integer idJustificativa;
    private Timestamp dataHora;
    private String complementoJustificativa;
    private String observacoes;
    private String aprovacao;

    public Integer getIdAprovacaoSolicitacaoServico() {
        return idAprovacaoSolicitacaoServico;
    }

    public void setIdAprovacaoSolicitacaoServico(final Integer parm) {
        idAprovacaoSolicitacaoServico = parm;
    }

    public Integer getIdSolicitacaoServico() {
        return idSolicitacaoServico;
    }

    public void setIdSolicitacaoServico(final Integer parm) {
        idSolicitacaoServico = parm;
    }

    public Integer getIdResponsavel() {
        return idResponsavel;
    }

    public void setIdResponsavel(final Integer parm) {
        idResponsavel = parm;
    }

    public Integer getIdTarefa() {
        return idTarefa;
    }

    public void setIdTarefa(final Integer parm) {
        idTarefa = parm;
    }

    public Integer getIdJustificativa() {
        return idJustificativa;
    }

    public void setIdJustificativa(final Integer parm) {
        idJustificativa = parm;
    }

    public Timestamp getDataHora() {
        return dataHora;
    }

    public void setDataHora(final Timestamp parm) {
        dataHora = parm;
    }

    public String getComplementoJustificativa() {
        return complementoJustificativa;
    }

    public void setComplementoJustificativa(final String parm) {
        complementoJustificativa = parm;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(final String parm) {
        observacoes = parm;
    }

    public String getAprovacao() {
        return aprovacao;
    }

    public void setAprovacao(final String parm) {
        aprovacao = parm;
    }

    public String getDataHoraStr() {
        final SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return format.format(dataHora);
    }

}
