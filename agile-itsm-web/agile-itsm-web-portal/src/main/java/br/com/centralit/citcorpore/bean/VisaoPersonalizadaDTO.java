package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class VisaoPersonalizadaDTO extends BaseEntity {

    private Integer idvisao;
    private String personalizada;
    private java.sql.Date dataModif;

    private String[] identifPersonalizado;
    private String[] carregar;

    public Integer getIdvisao() {
        return idvisao;
    }

    public void setIdvisao(final Integer parm) {
        idvisao = parm;
    }

    public String getPersonalizada() {
        return personalizada;
    }

    public void setPersonalizada(final String parm) {
        personalizada = parm;
    }

    public java.sql.Date getDataModif() {
        return dataModif;
    }

    public void setDataModif(final java.sql.Date parm) {
        dataModif = parm;
    }

    public String[] getIdentifPersonalizado() {
        return identifPersonalizado;
    }

    public void setIdentifPersonalizado(final String[] identifPersonalizado) {
        this.identifPersonalizado = identifPersonalizado;
    }

    public String[] getCarregar() {
        return carregar;
    }

    public void setCarregar(final String[] carregar) {
        this.carregar = carregar;
    }

}
