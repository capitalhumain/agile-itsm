package br.com.centralit.citcorpore.bean;

import java.sql.Date;

import br.com.agileitsm.model.support.BaseEntity;

public class HistoricoRespCentroResultadoDTO extends BaseEntity {

    private Integer idHistoricoRespCentroResultado;
    private Integer idResponsavel;
    private Integer idCentroResultado;
    private Date dataInicio;
    private Date dataFim;

    private String nomeEmpregado;

    public Integer getIdHistoricoRespCentroResultado() {
        return idHistoricoRespCentroResultado;
    }

    public void setIdHistoricoRespCentroResultado(final Integer parm) {
        idHistoricoRespCentroResultado = parm;
    }

    public Integer getIdResponsavel() {
        return idResponsavel;
    }

    public void setIdResponsavel(final Integer parm) {
        idResponsavel = parm;
    }

    public Integer getIdCentroResultado() {
        return idCentroResultado;
    }

    public void setIdCentroResultado(final Integer parm) {
        idCentroResultado = parm;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(final Date parm) {
        dataInicio = parm;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(final Date parm) {
        dataFim = parm;
    }

    public String getNomeEmpregado() {
        return nomeEmpregado;
    }

    public void setNomeEmpregado(final String nomeEmpregado) {
        this.nomeEmpregado = nomeEmpregado;
    }

}
