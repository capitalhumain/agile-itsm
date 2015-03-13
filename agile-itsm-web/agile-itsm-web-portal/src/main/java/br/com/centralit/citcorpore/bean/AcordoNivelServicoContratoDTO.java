package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class AcordoNivelServicoContratoDTO extends BaseEntity {

    private Integer idAcordoNivelServicoContrato;
    private Integer idContrato;
    private String descricaoAcordo;
    private String detalhamentoAcordo;
    private Double valorLimite;
    private String unidadeValorLimite;
    private java.sql.Date dataInicio;
    private java.sql.Date dataFim;
    private String descricaoGlosa;
    private Integer idFormula;

    public Integer getIdAcordoNivelServicoContrato() {
        return idAcordoNivelServicoContrato;
    }

    public void setIdAcordoNivelServicoContrato(final Integer parm) {
        idAcordoNivelServicoContrato = parm;
    }

    public Integer getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(final Integer parm) {
        idContrato = parm;
    }

    public String getDescricaoAcordo() {
        return descricaoAcordo;
    }

    public void setDescricaoAcordo(final String parm) {
        descricaoAcordo = parm;
    }

    public String getDetalhamentoAcordo() {
        return detalhamentoAcordo;
    }

    public void setDetalhamentoAcordo(final String parm) {
        detalhamentoAcordo = parm;
    }

    public Double getValorLimite() {
        return valorLimite;
    }

    public void setValorLimite(final Double parm) {
        valorLimite = parm;
    }

    public String getUnidadeValorLimite() {
        return unidadeValorLimite;
    }

    public void setUnidadeValorLimite(final String parm) {
        unidadeValorLimite = parm;
    }

    public java.sql.Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(final java.sql.Date parm) {
        dataInicio = parm;
    }

    public java.sql.Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(final java.sql.Date parm) {
        dataFim = parm;
    }

    public String getDescricaoGlosa() {
        return descricaoGlosa;
    }

    public void setDescricaoGlosa(final String parm) {
        descricaoGlosa = parm;
    }

    public Integer getIdFormula() {
        return idFormula;
    }

    public void setIdFormula(final Integer idFormula) {
        this.idFormula = idFormula;
    }

}
