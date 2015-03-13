package br.com.centralit.citcorpore.bean;

import java.sql.Timestamp;

import br.com.agileitsm.model.support.BaseEntity;

public class HistoricoSituacaoCotacaoDTO extends BaseEntity {

    private Integer idHistorico;
    private Integer idCotacao;
    private Integer idResponsavel;
    private Timestamp dataHora;
    private String situacao;

    public Integer getIdHistorico() {
        return idHistorico;
    }

    public void setIdHistorico(final Integer parm) {
        idHistorico = parm;
    }

    public Integer getIdCotacao() {
        return idCotacao;
    }

    public void setIdCotacao(final Integer parm) {
        idCotacao = parm;
    }

    public Integer getIdResponsavel() {
        return idResponsavel;
    }

    public void setIdResponsavel(final Integer parm) {
        idResponsavel = parm;
    }

    public Timestamp getDataHora() {
        return dataHora;
    }

    public void setDataHora(final Timestamp parm) {
        dataHora = parm;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(final String parm) {
        situacao = parm;
    }

}
