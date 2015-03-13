package br.com.centralit.citcorpore.bean;

import java.sql.Date;

import br.com.agileitsm.model.support.BaseEntity;

/**
 * @author Pedro
 *
 */
public class ControleContratoPagamentoDTO extends BaseEntity {

    private Integer idCcPagamento;
    private Integer idControleContrato;
    private Date dataCcPagamento;
    private Date dataAtrasoCcPagamento;
    private Integer parcelaCcPagamento;

    public Integer getIdCcPagamento() {
        return idCcPagamento;
    }

    public void setIdCcPagamento(final Integer idCcPagamento) {
        this.idCcPagamento = idCcPagamento;
    }

    public Integer getIdControleContrato() {
        return idControleContrato;
    }

    public void setIdControleContrato(final Integer idControleContrato) {
        this.idControleContrato = idControleContrato;
    }

    public Date getDataCcPagamento() {
        return dataCcPagamento;
    }

    public void setDataCcPagamento(final Date dataCcPagamento) {
        this.dataCcPagamento = dataCcPagamento;
    }

    public Date getDataAtrasoCcPagamento() {
        return dataAtrasoCcPagamento;
    }

    public void setDataAtrasoCcPagamento(final Date dataAtrasoCcPagamento) {
        this.dataAtrasoCcPagamento = dataAtrasoCcPagamento;
    }

    public Integer getParcelaCcPagamento() {
        return parcelaCcPagamento;
    }

    public void setParcelaCcPagamento(final Integer parcelaCcPagamento) {
        this.parcelaCcPagamento = parcelaCcPagamento;
    }

}
