package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class RecursoTarefaLinBaseProjDTO extends BaseEntity {

    private Integer idRecursoTarefaLinBaseProj;
    private Integer idTarefaLinhaBaseProjeto;
    private Integer idPerfilContrato;
    private Integer idEmpregado;
    private Double percentualAloc;
    private String tempoAloc;
    private Double percentualExec;
    private Double tempoAlocMinutos;
    private Double custo;
    private Double custoPerfil;
    private String esforcoPorOS;

    private EmpregadoDTO empregadoDTO;

    private double tempoAlocEmMinutos = 0;

    public Integer getIdRecursoTarefaLinBaseProj() {
        return idRecursoTarefaLinBaseProj;
    }

    public void setIdRecursoTarefaLinBaseProj(final Integer parm) {
        idRecursoTarefaLinBaseProj = parm;
    }

    public Integer getIdTarefaLinhaBaseProjeto() {
        return idTarefaLinhaBaseProjeto;
    }

    public void setIdTarefaLinhaBaseProjeto(final Integer parm) {
        idTarefaLinhaBaseProjeto = parm;
    }

    public Integer getIdPerfilContrato() {
        return idPerfilContrato;
    }

    public void setIdPerfilContrato(final Integer parm) {
        idPerfilContrato = parm;
    }

    public Integer getIdEmpregado() {
        return idEmpregado;
    }

    public void setIdEmpregado(final Integer parm) {
        idEmpregado = parm;
    }

    public Double getPercentualAloc() {
        return percentualAloc;
    }

    public void setPercentualAloc(final Double parm) {
        percentualAloc = parm;
    }

    public String getTempoAloc() {
        return tempoAloc;
    }

    public void setTempoAloc(final String tempoAloc) {
        this.tempoAloc = tempoAloc;
    }

    public Double getPercentualExec() {
        return percentualExec;
    }

    public void setPercentualExec(final Double percentualExec) {
        this.percentualExec = percentualExec;
    }

    public double getTempoAlocEmMinutos() {
        return tempoAlocEmMinutos;
    }

    public void setTempoAlocEmMinutos(final double tempoAlocEmMinutos) {
        this.tempoAlocEmMinutos = tempoAlocEmMinutos;
    }

    public EmpregadoDTO getEmpregadoDTO() {
        return empregadoDTO;
    }

    public void setEmpregadoDTO(final EmpregadoDTO empregadoDTO) {
        this.empregadoDTO = empregadoDTO;
    }

    public Double getTempoAlocMinutos() {
        return tempoAlocMinutos;
    }

    public void setTempoAlocMinutos(final Double tempoAlocMinutos) {
        this.tempoAlocMinutos = tempoAlocMinutos;
    }

    public Double getCusto() {
        return custo;
    }

    public void setCusto(final Double custo) {
        this.custo = custo;
    }

    public Double getCustoPerfil() {
        return custoPerfil;
    }

    public void setCustoPerfil(final Double custoPerfil) {
        this.custoPerfil = custoPerfil;
    }

    public String getEsforcoPorOS() {
        return esforcoPorOS;
    }

    public void setEsforcoPorOS(final String esforcoPorOS) {
        this.esforcoPorOS = esforcoPorOS;
    }

}
