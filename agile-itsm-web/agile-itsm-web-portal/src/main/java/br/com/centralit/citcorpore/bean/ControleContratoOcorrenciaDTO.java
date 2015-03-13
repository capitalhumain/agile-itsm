package br.com.centralit.citcorpore.bean;

import java.sql.Date;

import br.com.agileitsm.model.support.BaseEntity;

/**
 * @author Pedro
 *
 */
public class ControleContratoOcorrenciaDTO extends BaseEntity {

    private Integer idCcOcorrencia;
    private Integer idControleContrato;
    private Integer idEmpregadoOcorrencia;

    private Date dataCcOcorrencia;
    private String assuntoCcOcorrencia;
    private String empregadoCcOcorrencia;

    public String getEmpregadoCcOcorrencia() {
        return empregadoCcOcorrencia;
    }

    public void setEmpregadoCcOcorrencia(final String empregadoCcOcorrencia) {
        this.empregadoCcOcorrencia = empregadoCcOcorrencia;
    }

    public Integer getIdCcOcorrencia() {
        return idCcOcorrencia;
    }

    public void setIdCcOcorrencia(final Integer idCcOcorrencia) {
        this.idCcOcorrencia = idCcOcorrencia;
    }

    public Integer getIdControleContrato() {
        return idControleContrato;
    }

    public void setIdControleContrato(final Integer idControleContrato) {
        this.idControleContrato = idControleContrato;
    }

    public Integer getIdEmpregadoOcorrencia() {
        return idEmpregadoOcorrencia;
    }

    public void setIdEmpregadoOcorrencia(final Integer idEmpregadoOcorrencia) {
        this.idEmpregadoOcorrencia = idEmpregadoOcorrencia;
    }

    public Date getDataCcOcorrencia() {
        return dataCcOcorrencia;
    }

    public void setDataCcOcorrencia(final Date dataCcOcorrencia) {
        this.dataCcOcorrencia = dataCcOcorrencia;
    }

    public String getAssuntoCcOcorrencia() {
        return assuntoCcOcorrencia;
    }

    public void setAssuntoCcOcorrencia(final String assuntoCcOcorrencia) {
        this.assuntoCcOcorrencia = assuntoCcOcorrencia;
    }

}
