package br.com.centralit.citgerencial.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class GerencialGraphInformationDTO extends BaseEntity {

    private static final long serialVersionUID = -3818973074788560743L;

    private String type;
    private boolean legend;

    public boolean isLegend() {
        return legend;
    }

    public void setLegend(final boolean legend) {
        this.legend = legend;
    }

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

}
