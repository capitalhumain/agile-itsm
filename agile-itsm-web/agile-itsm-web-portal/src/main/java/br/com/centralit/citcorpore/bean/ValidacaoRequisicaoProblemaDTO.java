package br.com.centralit.citcorpore.bean;

import java.sql.Date;

import br.com.agileitsm.model.support.BaseEntity;

/**
 *
 * @author geber.costa
 *
 */
public class ValidacaoRequisicaoProblemaDTO extends BaseEntity {

    private Integer idValidacaoRequisicaoProblema;
    private String observacaoProblema;
    private Date dataInicio;
    private Date dataFim;
    private Integer idProblema;

    public Integer getIdValidacaoRequisicaoProblema() {
        return idValidacaoRequisicaoProblema;
    }

    public void setIdValidacaoRequisicaoProblema(final Integer idValidacaoRequisicaoProblema) {
        this.idValidacaoRequisicaoProblema = idValidacaoRequisicaoProblema;
    }

    public String getObservacaoProblema() {
        return observacaoProblema;
    }

    public void setObservacaoProblema(final String observacaoProblema) {
        this.observacaoProblema = observacaoProblema;
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

    public Integer getIdProblema() {
        return idProblema;
    }

    public void setIdProblema(final Integer idProblema) {
        this.idProblema = idProblema;
    }

}
