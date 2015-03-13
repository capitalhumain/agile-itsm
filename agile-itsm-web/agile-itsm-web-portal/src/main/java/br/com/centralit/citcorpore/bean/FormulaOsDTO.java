package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class FormulaOsDTO extends BaseEntity {

    private static final long serialVersionUID = 1L;
    private Integer idFormulaOs;
    private String descricao;
    private String formula;
    private String situacao;
    private String formulaSimulada;

    public String getFormulaSimulada() {
        return formulaSimulada;
    }

    public void setFormulaSimulada(final String formulaSimulada) {
        this.formulaSimulada = formulaSimulada;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(final String situacao) {
        this.situacao = situacao;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public Integer getIdFormulaOs() {
        return idFormulaOs;
    }

    public void setIdFormulaOs(final Integer idFormulaOs) {
        this.idFormulaOs = idFormulaOs;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(final String descricao) {
        this.descricao = descricao;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(final String formula) {
        this.formula = formula;
    }

}
