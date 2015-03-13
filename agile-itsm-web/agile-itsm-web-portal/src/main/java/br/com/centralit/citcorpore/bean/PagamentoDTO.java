package br.com.centralit.citcorpore.bean;

import java.sql.Date;

import br.com.agileitsm.model.support.BaseEntity;

/**
 * @author Pedro
 *
 */
public class PagamentoDTO extends BaseEntity {

    private Integer idPagamento;
    private Integer parcela;
    private Date dataPagamento;

    public Integer getIdPagamento() {
        return idPagamento;
    }

    public void setIdPagamento(final Integer idPagamento) {
        this.idPagamento = idPagamento;
    }

    public Integer getParcela() {
        return parcela;
    }

    public void setParcela(final Integer parcela) {
        this.parcela = parcela;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(final Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

}
