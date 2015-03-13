package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class RecursoProjetoDTO extends BaseEntity {

    private Integer idProjeto;
    private Integer idEmpregado;
    private Double custoHora;

    private EmpregadoDTO empregadoDTO;

    public Integer getIdProjeto() {
        return idProjeto;
    }

    public void setIdProjeto(final Integer parm) {
        idProjeto = parm;
    }

    public Integer getIdEmpregado() {
        return idEmpregado;
    }

    public void setIdEmpregado(final Integer parm) {
        idEmpregado = parm;
    }

    public Double getCustoHora() {
        return custoHora;
    }

    public void setCustoHora(final Double parm) {
        custoHora = parm;
    }

    public EmpregadoDTO getEmpregadoDTO() {
        return empregadoDTO;
    }

    public void setEmpregadoDTO(final EmpregadoDTO empregadoDTO) {
        this.empregadoDTO = empregadoDTO;
    }

}
