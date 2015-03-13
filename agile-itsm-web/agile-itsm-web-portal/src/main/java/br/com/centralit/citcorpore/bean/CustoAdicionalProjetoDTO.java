package br.com.centralit.citcorpore.bean;

import java.sql.Date;

import br.com.agileitsm.model.support.BaseEntity;

public class CustoAdicionalProjetoDTO extends BaseEntity {

    private static final long serialVersionUID = -7325677451875705649L;

    private Integer idCustoAdicional;
    private Integer idProjeto;
    private String tipoCusto;
    private Double valor;
    private String detalhamento;
    private Integer idCliente;
    private Date dataCusto;

    public String getDetalhamento() {
        return detalhamento;
    }

    public void setDetalhamento(final String detalhamento) {
        this.detalhamento = detalhamento;
    }

    public Integer getIdCustoAdicional() {
        return idCustoAdicional;
    }

    public void setIdCustoAdicional(final Integer idCustoAdicional) {
        this.idCustoAdicional = idCustoAdicional;
    }

    public Integer getIdProjeto() {
        return idProjeto;
    }

    public void setIdProjeto(final Integer idProjeto) {
        this.idProjeto = idProjeto;
    }

    public String getTipoCusto() {
        return tipoCusto;
    }

    public void setTipoCusto(final String tipoCusto) {
        this.tipoCusto = tipoCusto;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(final Double valor) {
        this.valor = valor;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(final Integer idCliente) {
        this.idCliente = idCliente;
    }

    public Date getDataCusto() {
        return dataCusto;
    }

    public void setDataCusto(final Date dataCusto) {
        this.dataCusto = dataCusto;
    }

}
