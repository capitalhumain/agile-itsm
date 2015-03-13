package br.com.centralit.citcorpore.metainfo.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class ReturnTableSearchDTO extends BaseEntity {

    private String label;
    private String value;
    private String[][] aaData;

    public String getLabel() {
        return label;
    }

    public void setLabel(final String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(final String value) {
        this.value = value;
    }

    public String[][] getAaData() {
        return aaData;
    }

    public void setAaData(final String[][] aaData) {
        this.aaData = aaData;
    }

}
