package br.com.centralit.citcorpore.bean;

import java.sql.Timestamp;

import br.com.agileitsm.model.support.BaseEntity;

public class HistoricoItemRequisicaoDTO extends BaseEntity {

    private Integer idHistorico;
    private Integer idItemRequisicao;
    private Integer idResponsavel;
    private Timestamp dataHora;
    private String acao;
    private String situacao;
    private String complemento;
    private String atributosAnteriores;
    private String atributosAtuais;

    public Integer getIdHistorico() {
        return idHistorico;
    }

    public void setIdHistorico(final Integer idHistorico) {
        this.idHistorico = idHistorico;
    }

    public Integer getIdItemRequisicao() {
        return idItemRequisicao;
    }

    public void setIdItemRequisicao(final Integer idItemRequisicao) {
        this.idItemRequisicao = idItemRequisicao;
    }

    public Integer getIdResponsavel() {
        return idResponsavel;
    }

    public void setIdResponsavel(final Integer idResponsavel) {
        this.idResponsavel = idResponsavel;
    }

    public Timestamp getDataHora() {
        return dataHora;
    }

    public void setDataHora(final Timestamp dataHora) {
        this.dataHora = dataHora;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(final String situacao) {
        this.situacao = situacao;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(final String complemento) {
        this.complemento = complemento;
    }

    public String getAcao() {
        return acao;
    }

    public void setAcao(final String acao) {
        this.acao = acao;
    }

    public String getAtributosAnteriores() {
        return atributosAnteriores;
    }

    public void setAtributosAnteriores(final String atributosAnteriores) {
        this.atributosAnteriores = atributosAnteriores;
    }

    public String getAtributosAtuais() {
        return atributosAtuais;
    }

    public void setAtributosAtuais(final String atributosAtuais) {
        this.atributosAtuais = atributosAtuais;
    }

}
