package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class TimeSheetProjetoDTO extends BaseEntity {

    private Integer idTimeSheetProjeto;
    private Integer idRecursoTarefaLinBaseProj;
    private java.sql.Timestamp dataHoraReg;
    private java.sql.Date data;
    private String hora;
    private Double qtdeHoras;
    private Double custo;
    private String detalhamento;
    private Double percExecutado;

    private Integer idLinhaBaseProjeto;

    public Integer getIdTimeSheetProjeto() {
        return idTimeSheetProjeto;
    }

    public void setIdTimeSheetProjeto(final Integer parm) {
        idTimeSheetProjeto = parm;
    }

    public Integer getIdRecursoTarefaLinBaseProj() {
        return idRecursoTarefaLinBaseProj;
    }

    public void setIdRecursoTarefaLinBaseProj(final Integer parm) {
        idRecursoTarefaLinBaseProj = parm;
    }

    public java.sql.Timestamp getDataHoraReg() {
        return dataHoraReg;
    }

    public void setDataHoraReg(final java.sql.Timestamp parm) {
        dataHoraReg = parm;
    }

    public java.sql.Date getData() {
        return data;
    }

    public void setData(final java.sql.Date parm) {
        data = parm;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(final String parm) {
        hora = parm;
    }

    public Double getQtdeHoras() {
        return qtdeHoras;
    }

    public void setQtdeHoras(final Double parm) {
        qtdeHoras = parm;
    }

    public Double getCusto() {
        return custo;
    }

    public void setCusto(final Double parm) {
        custo = parm;
    }

    public String getDetalhamento() {
        return detalhamento;
    }

    public void setDetalhamento(final String parm) {
        detalhamento = parm;
    }

    public Double getPercExecutado() {
        return percExecutado;
    }

    public void setPercExecutado(final Double percExecutado) {
        this.percExecutado = percExecutado;
    }

    public Integer getIdLinhaBaseProjeto() {
        return idLinhaBaseProjeto;
    }

    public void setIdLinhaBaseProjeto(final Integer idLinhaBaseProjeto) {
        this.idLinhaBaseProjeto = idLinhaBaseProjeto;
    }

}
