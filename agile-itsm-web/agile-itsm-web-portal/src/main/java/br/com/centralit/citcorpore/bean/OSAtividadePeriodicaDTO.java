package br.com.centralit.citcorpore.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class OSAtividadePeriodicaDTO extends BaseEntity {

    private Integer idAtividadePeriodica;
    private Integer idOs;

    public Integer getIdOs() {
        return idOs;
    }

    public void setIdOs(final Integer parm) {
        idOs = parm;
    }

    /**
     * @return the idAtividadePeriodica
     */
    public Integer getIdAtividadePeriodica() {
        return idAtividadePeriodica;
    }

    /**
     * @param idAtividadePeriodica
     *            the idAtividadePeriodica to set
     */
    public void setIdAtividadePeriodica(final Integer idAtividadePeriodica) {
        this.idAtividadePeriodica = idAtividadePeriodica;
    }

}
