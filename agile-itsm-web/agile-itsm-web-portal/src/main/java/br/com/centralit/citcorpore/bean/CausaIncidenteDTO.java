package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class CausaIncidenteDTO extends BaseEntity {

    private Integer idCausaIncidente;
    private Integer idCausaIncidentePai;
    private String descricaoCausa;
    private java.sql.Date dataInicio;
    private java.sql.Date dataFim;

    private Integer nivel;

    public Integer getIdCausaIncidente() {
        return idCausaIncidente;
    }

    public void setIdCausaIncidente(final Integer parm) {
        idCausaIncidente = parm;
    }

    public Integer getIdCausaIncidentePai() {
        return idCausaIncidentePai;
    }

    public void setIdCausaIncidentePai(final Integer parm) {
        idCausaIncidentePai = parm;
    }

    public String getDescricaoCausa() {
        return descricaoCausa;
    }

    public void setDescricaoCausa(final String parm) {
        descricaoCausa = parm;
    }

    public String getDescricaoCausaNivel() {
        if (this.getNivel() == null) {
            return descricaoCausa;
        }
        String str = "";
        for (int i = 0; i < this.getNivel().intValue(); i++) {
            str += "....";
        }
        return str + descricaoCausa;
    }

    public java.sql.Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(final java.sql.Date parm) {
        dataInicio = parm;
    }

    public java.sql.Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(final java.sql.Date parm) {
        dataFim = parm;
    }

    public Integer getNivel() {
        return nivel;
    }

    public void setNivel(final Integer nivel) {
        this.nivel = nivel;
    }

}
