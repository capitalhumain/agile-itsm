package br.com.citframework.dto;

import br.com.agileitsm.model.support.BaseEntity;

public class ReflexaoCopyProperty extends BaseEntity {

    private static final long serialVersionUID = 5025697250195959285L;
    private String namePropertySource;
    private String namePropertyDest;

    public ReflexaoCopyProperty(final String namePropSource, final String namePropDest) {
        namePropertySource = namePropSource;
        namePropertyDest = namePropDest;
    }

    public String getNamePropertyDest() {
        return namePropertyDest;
    }

    public void setNamePropertyDest(final String namePropertyDest) {
        this.namePropertyDest = namePropertyDest;
    }

    public String getNamePropertySource() {
        return namePropertySource;
    }

    public void setNamePropertySource(final String namePropertySource) {
        this.namePropertySource = namePropertySource;
    }

}
