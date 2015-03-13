package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class RelatorioOrdemServicoUstDTO extends BaseEntity {

    private Double ano;
    private Integer idContrato;
    private Double custoAtividade;
    private String custoAtividadeFormatada;
    private Double periodoDouble;
    private String mes;
    private String tipoEventoServico;
    private Integer quantidadePorTipoEventoServico;

    /**
     * @return the custoAtividade
     */
    public Double getCustoAtividade() {
        return custoAtividade;
    }

    /**
     * @param custoAtividade
     *            the custoAtividade to set
     */
    public void setCustoAtividade(final Double custoAtividade) {
        this.custoAtividade = custoAtividade;
    }

    /**
     * @return the periodo
     */
    public Integer getPeriodo() {
        return (int) (periodoDouble / 1);
    }

    /**
     * @return the mes
     */
    public String getMes() {
        return mes;
    }

    /**
     * @param mes
     *            the mes to set
     */
    public void setMes(final String mes) {
        this.mes = mes;
    }

    /**
     * @return the custoAtividadeFormatada
     */
    public String getCustoAtividadeFormatada() {
        return custoAtividadeFormatada;
    }

    /**
     * @param custoAtividadeFormatada
     *            the custoAtividadeFormatada to set
     */
    public void setCustoAtividadeFormatada(final String custoAtividadeFormatada) {
        this.custoAtividadeFormatada = custoAtividadeFormatada;
    }

    /**
     * @return the tipoEventoServico
     */
    public String getTipoEventoServico() {
        return tipoEventoServico;
    }

    /**
     * @param tipoEventoServico
     *            the tipoEventoServico to set
     */
    public void setTipoEventoServico(final String tipoEventoServico) {
        this.tipoEventoServico = tipoEventoServico;
    }

    /**
     * @return the quantidadePorTipoEventoServico
     */
    public Integer getQuantidadePorTipoEventoServico() {
        return quantidadePorTipoEventoServico;
    }

    /**
     * @param quantidadePorTipoEventoServico
     *            the quantidadePorTipoEventoServico to set
     */
    public void setQuantidadePorTipoEventoServico(final Integer quantidadePorTipoEventoServico) {
        this.quantidadePorTipoEventoServico = quantidadePorTipoEventoServico;
    }

    /**
     * @return the idContrato
     */
    public Integer getIdContrato() {
        return idContrato;
    }

    /**
     * @param idContrato
     *            the idContrato to set
     */
    public void setIdContrato(final Integer idContrato) {
        this.idContrato = idContrato;
    }

    public Double getAno() {
        return ano;
    }

    public void setAno(final Double ano) {
        this.ano = ano;
    }

    public Integer getAnoInteger() {
        return (int) (ano / 1);
    }

    public Double getPeriodoDouble() {
        return periodoDouble;
    }

    public void setPeriodoDouble(final Double periodoDouble) {
        this.periodoDouble = periodoDouble;
    }

}
