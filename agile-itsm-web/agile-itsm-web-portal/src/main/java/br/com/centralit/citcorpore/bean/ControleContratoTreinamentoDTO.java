package br.com.centralit.citcorpore.bean;

import java.sql.Date;

import br.com.agileitsm.model.support.BaseEntity;

/**
 * @author Pedro
 *
 */
public class ControleContratoTreinamentoDTO extends BaseEntity {

    private Integer idCcTreinamento;
    private Integer idControleContrato;
    private Integer idEmpregadoTreinamento;
    private Date dataCcTreinamento;
    private String nomeCcTreinamento;
    private String nomeInstrutorCcTreinamento;

    public String getNomeInstrutorCcTreinamento() {
        return nomeInstrutorCcTreinamento;
    }

    public void setNomeInstrutorCcTreinamento(final String nomeInstrutorCcTreinamento) {
        this.nomeInstrutorCcTreinamento = nomeInstrutorCcTreinamento;
    }

    public Integer getIdCcTreinamento() {
        return idCcTreinamento;
    }

    public void setIdCcTreinamento(final Integer idCcTreinamento) {
        this.idCcTreinamento = idCcTreinamento;
    }

    public Integer getIdControleContrato() {
        return idControleContrato;
    }

    public void setIdControleContrato(final Integer idControleContrato) {
        this.idControleContrato = idControleContrato;
    }

    public Integer getIdEmpregadoTreinamento() {
        return idEmpregadoTreinamento;
    }

    public void setIdEmpregadoTreinamento(final Integer idEmpregadoTreinamento) {
        this.idEmpregadoTreinamento = idEmpregadoTreinamento;
    }

    public Date getDataCcTreinamento() {
        return dataCcTreinamento;
    }

    public void setDataCcTreinamento(final Date dataCcTreinamento) {
        this.dataCcTreinamento = dataCcTreinamento;
    }

    public String getNomeCcTreinamento() {
        return nomeCcTreinamento;
    }

    public void setNomeCcTreinamento(final String nomeCcTreinamento) {
        this.nomeCcTreinamento = nomeCcTreinamento;
    }

}
