package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class ValorLimiteAprovacaoDTO extends BaseEntity {

    private Integer idValorLimiteAprovacao;
    private Integer idLimiteAprovacao;
    private String tipoUtilizacao;
    private String tipoLimite;
    private Double valorLimite;
    private Integer intervaloDias;

    private Integer sequencia;

    public Integer getIdValorLimiteAprovacao() {
        return idValorLimiteAprovacao;
    }

    public void setIdValorLimiteAprovacao(final Integer parm) {
        idValorLimiteAprovacao = parm;
    }

    public Integer getIdLimiteAprovacao() {
        return idLimiteAprovacao;
    }

    public void setIdLimiteAprovacao(final Integer parm) {
        idLimiteAprovacao = parm;
    }

    public String getTipoUtilizacao() {
        return tipoUtilizacao;
    }

    public void setTipoUtilizacao(final String parm) {
        tipoUtilizacao = parm;
    }

    public String getTipoLimite() {
        return tipoLimite;
    }

    public void setTipoLimite(final String parm) {
        tipoLimite = parm;
    }

    public Double getValorLimite() {
        return valorLimite;
    }

    public void setValorLimite(final Double parm) {
        valorLimite = parm;
    }

    public Integer getIntervaloDias() {
        return intervaloDias;
    }

    public void setIntervaloDias(final Integer parm) {
        intervaloDias = parm;
    }

    public Integer getSequencia() {
        return sequencia;
    }

    public void setSequencia(final Integer sequencia) {
        this.sequencia = sequencia;
    }

}
