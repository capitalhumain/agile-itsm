package br.com.centralit.citcorpore.bean;

import java.sql.Date;

import br.com.agileitsm.model.support.BaseEntity;

public class ExcecaoCalendarioDTO extends BaseEntity {

    private Integer idExcecaoCalendario;
    private Integer idCalendario;
    private Integer idJornada;
    private String tipo;
    private Date dataInicio;
    private Date dataTermino;

    public Integer getIdExcecaoCalendario() {
        return idExcecaoCalendario;
    }

    public void setIdExcecaoCalendario(final Integer parm) {
        idExcecaoCalendario = parm;
    }

    public Integer getIdCalendario() {
        return idCalendario;
    }

    public void setIdCalendario(final Integer parm) {
        idCalendario = parm;
    }

    public Integer getIdJornada() {
        return idJornada;
    }

    public void setIdJornada(final Integer parm) {
        idJornada = parm;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(final String parm) {
        tipo = parm;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(final Date parm) {
        dataInicio = parm;
    }

    public Date getDataTermino() {
        return dataTermino;
    }

    public void setDataTermino(final Date parm) {
        dataTermino = parm;
    }

}
