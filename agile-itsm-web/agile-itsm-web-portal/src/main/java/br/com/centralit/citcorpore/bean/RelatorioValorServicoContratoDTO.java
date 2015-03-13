/**
 *
 */
package br.com.centralit.citcorpore.bean;

import java.math.BigDecimal;
import java.util.Date;

import br.com.agileitsm.model.support.BaseEntity;

public class RelatorioValorServicoContratoDTO extends BaseEntity {

    private static final long serialVersionUID = 5769173299912237423L;

    private String nomeServico;

    private Integer quantidade;
    private BigDecimal valorServico;
    private BigDecimal totalValorServico;
    private BigDecimal totalGeral;
    private java.util.Date dataInicio;
    private java.util.Date dataFim;
    private Integer idServicoContrato;

    public String getNomeServico() {
        return nomeServico;
    }

    public void setNomeServico(final String nomeServico) {
        this.nomeServico = nomeServico;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(final Integer quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getValorServico() {
        return valorServico;
    }

    public void setValorServico(final BigDecimal valorServico) {
        this.valorServico = valorServico;
    }

    public BigDecimal getTotalValorServico() {
        return totalValorServico;
    }

    public void setTotalValorServico(final BigDecimal totalValorServico) {
        this.totalValorServico = totalValorServico;
    }

    public BigDecimal getTotalGeral() {
        return totalGeral;
    }

    public void setTotalGeral(final BigDecimal totalGeral) {
        this.totalGeral = totalGeral;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(final Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(final Date dataFim) {
        this.dataFim = dataFim;
    }

    public Integer getIdServicoContrato() {
        return idServicoContrato;
    }

    public void setIdServicoContrato(final Integer idServicoContrato) {
        this.idServicoContrato = idServicoContrato;
    }

}
