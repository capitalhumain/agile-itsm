package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class FaixaValoresRecursoDTO extends BaseEntity {

    private Integer idFaixaValoresRecurso;
    private Integer idRecurso;
    private Double valorInicio;
    private Double valorFim;
    private String cor;
    private String descricao;

    public String getCorInner() {
        return "<div style=\"width:100px; height:18px; background-color: " + this.getCor() + "\" >&nbsp;</div>";
    }

    public Integer getIdFaixaValoresRecurso() {
        return idFaixaValoresRecurso;
    }

    public void setIdFaixaValoresRecurso(final Integer parm) {
        idFaixaValoresRecurso = parm;
    }

    public Integer getIdRecurso() {
        return idRecurso;
    }

    public void setIdRecurso(final Integer parm) {
        idRecurso = parm;
    }

    public Double getValorInicio() {
        return valorInicio;
    }

    public void setValorInicio(final Double parm) {
        valorInicio = parm;
    }

    public Double getValorFim() {
        return valorFim;
    }

    public void setValorFim(final Double parm) {
        valorFim = parm;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(final String parm) {
        cor = parm;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(final String parm) {
        descricao = parm;
    }

}
