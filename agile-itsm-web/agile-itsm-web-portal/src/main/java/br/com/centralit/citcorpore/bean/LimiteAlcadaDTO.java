package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class LimiteAlcadaDTO extends BaseEntity {

    private Integer idLimiteAlcada;
    private Integer idAlcada;
    private Integer idGrupo;
    private String tipoLimite;
    private String abrangenciaCentroCusto;
    private Double limiteItemUsoInterno;
    private Double limiteMensalUsoInterno;
    private Double limiteItemAtendCliente;
    private Double limiteMensalAtendCliente;
    private String situacao;

    public Integer getIdLimiteAlcada() {
        return idLimiteAlcada;
    }

    public void setIdLimiteAlcada(final Integer parm) {
        idLimiteAlcada = parm;
    }

    public Integer getIdAlcada() {
        return idAlcada;
    }

    public void setIdAlcada(final Integer parm) {
        idAlcada = parm;
    }

    public Integer getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(final Integer parm) {
        idGrupo = parm;
    }

    public String getTipoLimite() {
        return tipoLimite;
    }

    public void setTipoLimite(final String parm) {
        tipoLimite = parm;
    }

    public String getAbrangenciaCentroCusto() {
        return abrangenciaCentroCusto;
    }

    public void setAbrangenciaCentroCusto(final String parm) {
        abrangenciaCentroCusto = parm;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(final String parm) {
        situacao = parm;
    }

    public Double getLimiteItemUsoInterno() {
        return limiteItemUsoInterno;
    }

    public void setLimiteItemUsoInterno(final Double limiteItemUsoInterno) {
        this.limiteItemUsoInterno = limiteItemUsoInterno;
    }

    public Double getLimiteMensalUsoInterno() {
        return limiteMensalUsoInterno;
    }

    public void setLimiteMensalUsoInterno(final Double limiteMensalUsoInterno) {
        this.limiteMensalUsoInterno = limiteMensalUsoInterno;
    }

    public Double getLimiteItemAtendCliente() {
        return limiteItemAtendCliente;
    }

    public void setLimiteItemAtendCliente(final Double limiteItemAtendCliente) {
        this.limiteItemAtendCliente = limiteItemAtendCliente;
    }

    public Double getLimiteMensalAtendCliente() {
        return limiteMensalAtendCliente;
    }

    public void setLimiteMensalAtendCliente(final Double limiteMensalAtendCliente) {
        this.limiteMensalAtendCliente = limiteMensalAtendCliente;
    }

}
