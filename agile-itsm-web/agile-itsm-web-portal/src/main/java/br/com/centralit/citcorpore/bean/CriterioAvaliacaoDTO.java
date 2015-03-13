package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class CriterioAvaliacaoDTO extends BaseEntity {

    private Integer idCriterio;
    private String descricao;
    private String aplicavelCotacao;
    private String aplicavelAvaliacaoSolicitante;
    private String aplicavelAvaliacaoComprador;
    private String aplicavelQualificacaoFornecedor;
    private String tipoAvaliacao;

    private Integer sequencia;
    private String obs;
    private String valor;

    private Integer peso;

    public Integer getIdCriterio() {
        return idCriterio;
    }

    public void setIdCriterio(final Integer parm) {
        idCriterio = parm;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(final String parm) {
        descricao = parm;
    }

    public String getAplicavelCotacao() {
        return aplicavelCotacao;
    }

    public void setAplicavelCotacao(final String parm) {
        aplicavelCotacao = parm;
    }

    public String getAplicavelAvaliacaoSolicitante() {
        return aplicavelAvaliacaoSolicitante;
    }

    public void setAplicavelAvaliacaoSolicitante(final String parm) {
        aplicavelAvaliacaoSolicitante = parm;
    }

    public String getAplicavelAvaliacaoComprador() {
        return aplicavelAvaliacaoComprador;
    }

    public void setAplicavelAvaliacaoComprador(final String parm) {
        aplicavelAvaliacaoComprador = parm;
    }

    public Integer getSequencia() {
        return sequencia;
    }

    public void setSequencia(final Integer sequencia) {
        this.sequencia = sequencia;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(final String obs) {
        this.obs = obs;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(final String valor) {
        this.valor = valor;
    }

    public Integer getPeso() {
        return peso;
    }

    public void setPeso(final Integer peso) {
        this.peso = peso;
    }

    public String getAplicavelQualificacaoFornecedor() {
        return aplicavelQualificacaoFornecedor;
    }

    public void setAplicavelQualificacaoFornecedor(final String aplicavelQualificacaoFornecedor) {
        this.aplicavelQualificacaoFornecedor = aplicavelQualificacaoFornecedor;
    }

    public String getTipoAvaliacao() {
        return tipoAvaliacao;
    }

    public void setTipoAvaliacao(final String tipoAvaliacao) {
        this.tipoAvaliacao = tipoAvaliacao;
    }

}
